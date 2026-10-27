package com.example.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.data.model.Ayah
import com.example.data.model.Bookmark
import com.example.data.model.LastRead
import com.example.data.model.PrayerSchedule
import com.example.data.model.ReadingSettings
import com.example.data.model.Surah
import com.example.data.model.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.UUID

class QuranRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("yann_quran_prefs", Context.MODE_PRIVATE)

    private val cachedAyahs = mutableMapOf<Int, List<Ayah>>()

    private val _lastRead = MutableStateFlow(loadLastRead())
    val lastRead: StateFlow<LastRead> = _lastRead.asStateFlow()

    private val _bookmarks = MutableStateFlow(loadBookmarks())
    val bookmarks: StateFlow<List<Bookmark>> = _bookmarks.asStateFlow()

    private val _favoriteSurahIds = MutableStateFlow(loadFavorites())
    val favoriteSurahIds: StateFlow<Set<Int>> = _favoriteSurahIds.asStateFlow()

    private val _readingSettings = MutableStateFlow(loadSettings())
    val readingSettings: StateFlow<ReadingSettings> = _readingSettings.asStateFlow()

    private val _prayerSchedule = MutableStateFlow(PrayerSchedule())
    val prayerSchedule: StateFlow<PrayerSchedule> = _prayerSchedule.asStateFlow()

    fun getAllSurahs(): List<Surah> = QuranDataSource.surahs

    fun getDailyAyah(): Ayah = QuranDataSource.dailyVerse

    suspend fun getAyahsForSurah(surahNumber: Int): List<Ayah> {
        cachedAyahs[surahNumber]?.let { return it }

        val offline = QuranDataSource.getOfflineAyahsForSurah(surahNumber)

        val fetched = withContext(Dispatchers.IO) {
            try {
                fetchSurahFromNetwork(surahNumber)
            } catch (e: Exception) {
                Log.w("QuranRepository", "Network fetch failed for surah $surahNumber, using offline data", e)
                null
            }
        }

        val result = if (!fetched.isNullOrEmpty()) fetched else offline
        cachedAyahs[surahNumber] = result
        return result
    }

    private fun fetchSurahFromNetwork(surahNumber: Int): List<Ayah>? {
        val url = URL("https://equran.id/api/v2/surat/$surahNumber")
        val conn = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 5000
            readTimeout = 7000
            setRequestProperty("Accept", "application/json")
        }

        if (conn.responseCode != 200) return null

        val reader = BufferedReader(InputStreamReader(conn.inputStream))
        val response = reader.use { it.readText() }
        conn.disconnect()

        val json = JSONObject(response)
        val dataObj = json.optJSONObject("data") ?: return null
        val ayatArray = dataObj.optJSONArray("ayat") ?: return null

        val list = mutableListOf<Ayah>()
        for (i in 0 until ayatArray.length()) {
            val ayahObj = ayatArray.getJSONObject(i)
            val nomorAyat = ayahObj.getInt("nomorAyat")
            val teksArab = ayahObj.getString("teksArab")
            val teksLatin = ayahObj.optString("teksLatin", "")
            val teksIndonesia = ayahObj.optString("teksIndonesia", "")
            val audioObj = ayahObj.optJSONObject("audio")
            val audioUrl = audioObj?.optString("05")
                ?: audioObj?.optString("01")
                ?: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/${surahNumber}_$nomorAyat.mp3"

            list.add(
                Ayah(
                    numberInSurah = nomorAyat,
                    surahNumber = surahNumber,
                    arabicText = teksArab,
                    latinText = teksLatin,
                    translationText = teksIndonesia,
                    audioUrl = audioUrl,
                    juz = QuranDataSource.getSurahByNumber(surahNumber)?.juzStart ?: 1
                )
            )
        }
        return list
    }

    // Last Read persistence
    fun saveLastRead(surahNumber: Int, ayahNumber: Int) {
        val surah = QuranDataSource.getSurahByNumber(surahNumber) ?: return
        val newLastRead = LastRead(
            surahNumber = surahNumber,
            surahName = surah.name,
            ayahNumber = ayahNumber,
            juzNumber = surah.juzStart,
            timestamp = System.currentTimeMillis()
        )
        _lastRead.value = newLastRead
        prefs.edit().apply {
            putInt("last_surah_number", newLastRead.surahNumber)
            putString("last_surah_name", newLastRead.surahName)
            putInt("last_ayah_number", newLastRead.ayahNumber)
            putInt("last_juz_number", newLastRead.juzNumber)
            putLong("last_read_timestamp", newLastRead.timestamp)
            apply()
        }
    }

    private fun loadLastRead(): LastRead {
        val num = prefs.getInt("last_surah_number", 18) // Default Al-Kahf as requested
        val name = prefs.getString("last_surah_name", "Al-Kahf") ?: "Al-Kahf"
        val ayah = prefs.getInt("last_ayah_number", 10)
        val juz = prefs.getInt("last_juz_number", 15)
        val time = prefs.getLong("last_read_timestamp", System.currentTimeMillis())
        return LastRead(num, name, ayah, juz, time)
    }

    // Bookmarks persistence
    fun addBookmark(ayah: Ayah, surahName: String, note: String = "") {
        val id = UUID.randomUUID().toString()
        val bookmark = Bookmark(
            id = id,
            surahNumber = ayah.surahNumber,
            surahName = surahName,
            ayahNumber = ayah.numberInSurah,
            arabicSnippet = ayah.arabicText.take(60),
            translationSnippet = ayah.translationText.take(120),
            createdAt = System.currentTimeMillis(),
            note = note
        )
        val current = _bookmarks.value.toMutableList()
        current.removeAll { it.surahNumber == ayah.surahNumber && it.ayahNumber == ayah.numberInSurah }
        current.add(0, bookmark)
        _bookmarks.value = current
        saveBookmarksToPrefs(current)
    }

    fun removeBookmark(id: String) {
        val updated = _bookmarks.value.filter { it.id != id }
        _bookmarks.value = updated
        saveBookmarksToPrefs(updated)
    }

    fun isAyahBookmarked(surahNumber: Int, ayahNumber: Int): Boolean {
        return _bookmarks.value.any { it.surahNumber == surahNumber && it.ayahNumber == ayahNumber }
    }

    private fun saveBookmarksToPrefs(list: List<Bookmark>) {
        val set = list.map { "${it.id}|${it.surahNumber}|${it.surahName}|${it.ayahNumber}|${it.arabicSnippet}|${it.translationSnippet}|${it.createdAt}|${it.note}" }.toSet()
        prefs.edit().putStringSet("saved_bookmarks", set).apply()
    }

    private fun loadBookmarks(): List<Bookmark> {
        val set = prefs.getStringSet("saved_bookmarks", null) ?: return listOf(
            Bookmark(
                id = "default_bm_1",
                surahNumber = 18,
                surahName = "Al-Kahf",
                ayahNumber = 10,
                arabicSnippet = "إِذْ أَوَى الْفِتْيَةُ إِلَى الْكَهْفِ فَقَالُوا رَبَّنَا آتِنَا مِن لَّدُنكَ رَحْمَةً",
                translationSnippet = "(Ingatlah) ketika pemuda-pemuda itu berlindung ke dalam gua lalu mereka berdoa...",
                createdAt = System.currentTimeMillis(),
                note = "Doa perlindungan dan rahmat pemuda Al-Kahfi"
            ),
            Bookmark(
                id = "default_bm_2",
                surahNumber = 1,
                surahName = "Al-Fatihah",
                ayahNumber = 5,
                arabicSnippet = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
                translationSnippet = "Hanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami memohon pertolongan.",
                createdAt = System.currentTimeMillis() - 86400000,
                note = "Pondasi tauhid & ibadah"
            )
        )

        return set.mapNotNull { raw ->
            val parts = raw.split("|")
            if (parts.size >= 7) {
                Bookmark(
                    id = parts[0],
                    surahNumber = parts[1].toIntOrNull() ?: 1,
                    surahName = parts[2],
                    ayahNumber = parts[3].toIntOrNull() ?: 1,
                    arabicSnippet = parts[4],
                    translationSnippet = parts[5],
                    createdAt = parts[6].toLongOrNull() ?: 0L,
                    note = parts.getOrNull(7) ?: ""
                )
            } else null
        }.sortedByDescending { it.createdAt }
    }

    // Favorites
    fun toggleFavorite(surahNumber: Int) {
        val current = _favoriteSurahIds.value.toMutableSet()
        if (current.contains(surahNumber)) {
            current.remove(surahNumber)
        } else {
            current.add(surahNumber)
        }
        _favoriteSurahIds.value = current
        prefs.edit().putStringSet("favorite_surahs", current.map { it.toString() }.toSet()).apply()
    }

    private fun loadFavorites(): Set<Int> {
        val set = prefs.getStringSet("favorite_surahs", null)
        return if (set != null) {
            set.mapNotNull { it.toIntOrNull() }.toSet()
        } else {
            setOf(1, 18, 36, 55, 56, 67) // Common favored surahs
        }
    }

    // Reading Settings
    fun updateSettings(newSettings: ReadingSettings) {
        _readingSettings.value = newSettings
        prefs.edit().apply {
            putFloat("arabic_font_size", newSettings.arabicFontSize)
            putFloat("translation_font_size", newSettings.translationFontSize)
            putBoolean("show_latin", newSettings.showLatin)
            putBoolean("show_translation", newSettings.showTranslation)
            putString("theme_mode", newSettings.themeMode.name)
            putString("selected_reciter", newSettings.selectedReciter)
            apply()
        }
    }

    private fun loadSettings(): ReadingSettings {
        val arabicSize = prefs.getFloat("arabic_font_size", 28f)
        val transSize = prefs.getFloat("translation_font_size", 15f)
        val latin = prefs.getBoolean("show_latin", true)
        val trans = prefs.getBoolean("show_translation", true)
        val themeStr = prefs.getString("theme_mode", ThemeMode.SYSTEM.name) ?: ThemeMode.SYSTEM.name
        val theme = runCatching { ThemeMode.valueOf(themeStr) }.getOrDefault(ThemeMode.SYSTEM)
        val reciter = prefs.getString("selected_reciter", "Mishary Rashid Alafasy") ?: "Mishary Rashid Alafasy"

        return ReadingSettings(
            arabicFontSize = arabicSize,
            translationFontSize = transSize,
            showLatin = latin,
            showTranslation = trans,
            themeMode = theme,
            selectedReciter = reciter
        )
    }
}
