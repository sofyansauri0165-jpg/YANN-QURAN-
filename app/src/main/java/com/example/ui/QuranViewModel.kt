package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.QuranAudioPlayer
import com.example.data.QuranRepository
import com.example.data.model.Ayah
import com.example.data.model.Bookmark
import com.example.data.model.LastRead
import com.example.data.model.PrayerSchedule
import com.example.data.model.ReadingSettings
import com.example.data.model.Surah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class BottomNavTab {
    BERANDA, QURAN, BOOKMARK, PENGATURAN
}

enum class SurahFilter {
    ALL, MAKKIYAH, MADANIYAH, JUZ
}

class QuranViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = QuranRepository(application)
    val audioPlayer = QuranAudioPlayer(application)

    val lastRead: StateFlow<LastRead> = repository.lastRead
    val bookmarks: StateFlow<List<Bookmark>> = repository.bookmarks
    val favoriteSurahIds: StateFlow<Set<Int>> = repository.favoriteSurahIds
    val readingSettings: StateFlow<ReadingSettings> = repository.readingSettings
    val prayerSchedule: StateFlow<PrayerSchedule> = repository.prayerSchedule

    val isAudioPlaying: StateFlow<Boolean> = audioPlayer.isPlaying
    val currentPlayingAyah: StateFlow<Int?> = audioPlayer.currentPlayingAyah
    val currentPlayingSurah: StateFlow<Int?> = audioPlayer.currentPlayingSurah

    private val _currentTab = MutableStateFlow(BottomNavTab.BERANDA)
    val currentTab: StateFlow<BottomNavTab> = _currentTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _surahFilter = MutableStateFlow(SurahFilter.ALL)
    val surahFilter: StateFlow<SurahFilter> = _surahFilter.asStateFlow()

    private val _selectedJuz = MutableStateFlow(1)
    val selectedJuz: StateFlow<Int> = _selectedJuz.asStateFlow()

    private val allSurahs = repository.getAllSurahs()

    val filteredSurahs: StateFlow<List<Surah>> = combine(
        _searchQuery,
        _surahFilter,
        _selectedJuz
    ) { query, filter, juz ->
        var list = allSurahs
        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            list = list.filter {
                it.name.lowercase().contains(q) ||
                it.translation.lowercase().contains(q) ||
                it.arabicName.contains(q) ||
                it.number.toString() == q
            }
        }
        when (filter) {
            SurahFilter.ALL -> list
            SurahFilter.MAKKIYAH -> list.filter { it.revelationType.equals("Makkiyah", ignoreCase = true) }
            SurahFilter.MADANIYAH -> list.filter { it.revelationType.equals("Madaniyah", ignoreCase = true) }
            SurahFilter.JUZ -> list.filter { it.juzStart == juz }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allSurahs)

    // Reader state
    private val _activeSurah = MutableStateFlow<Surah?>(null)
    val activeSurah: StateFlow<Surah?> = _activeSurah.asStateFlow()

    private val _activeAyahs = MutableStateFlow<List<Ayah>>(emptyList())
    val activeAyahs: StateFlow<List<Ayah>> = _activeAyahs.asStateFlow()

    private val _isLoadingAyahs = MutableStateFlow(false)
    val isLoadingAyahs: StateFlow<Boolean> = _isLoadingAyahs.asStateFlow()

    private val _targetScrollAyah = MutableStateFlow(1)
    val targetScrollAyah: StateFlow<Int> = _targetScrollAyah.asStateFlow()

    val dailyVerse = repository.getDailyAyah()

    fun selectTab(tab: BottomNavTab) {
        _currentTab.value = tab
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSurahFilter(filter: SurahFilter) {
        _surahFilter.value = filter
    }

    fun setSelectedJuz(juz: Int) {
        _selectedJuz.value = juz
    }

    fun openSurah(surahNumber: Int, targetAyah: Int = 1) {
        val surah = allSurahs.find { it.number == surahNumber } ?: return
        _activeSurah.value = surah
        _targetScrollAyah.value = targetAyah
        _isLoadingAyahs.value = true

        viewModelScope.launch {
            val ayahs = repository.getAyahsForSurah(surahNumber)
            _activeAyahs.value = ayahs
            _isLoadingAyahs.value = false
        }
    }

    fun closeReader() {
        audioPlayer.stop()
        _activeSurah.value = null
        _activeAyahs.value = emptyList()
    }

    fun markAsLastRead(surahNumber: Int, ayahNumber: Int) {
        repository.saveLastRead(surahNumber, ayahNumber)
    }

    fun toggleBookmark(ayah: Ayah, surahName: String) {
        if (repository.isAyahBookmarked(ayah.surahNumber, ayah.numberInSurah)) {
            val bm = repository.bookmarks.value.find {
                it.surahNumber == ayah.surahNumber && it.ayahNumber == ayah.numberInSurah
            }
            if (bm != null) {
                repository.removeBookmark(bm.id)
            }
        } else {
            repository.addBookmark(ayah, surahName)
        }
    }

    fun isAyahBookmarked(surahNumber: Int, ayahNumber: Int): Boolean {
        return repository.isAyahBookmarked(surahNumber, ayahNumber)
    }

    fun deleteBookmark(id: String) {
        repository.removeBookmark(id)
    }

    fun toggleFavorite(surahNumber: Int) {
        repository.toggleFavorite(surahNumber)
    }

    fun playAyahAudio(ayah: Ayah) {
        audioPlayer.playAyah(ayah.surahNumber, ayah.numberInSurah, ayah.audioUrl) {
            // Optional auto-play next verse if available
            val nextAyah = _activeAyahs.value.find { it.numberInSurah == ayah.numberInSurah + 1 }
            if (nextAyah != null && nextAyah.audioUrl.isNotBlank()) {
                playAyahAudio(nextAyah)
            }
        }
    }

    fun togglePlayPause() {
        if (isAudioPlaying.value) {
            audioPlayer.pause()
        } else {
            audioPlayer.resume()
        }
    }

    fun stopAudio() {
        audioPlayer.stop()
    }

    fun updateSettings(settings: ReadingSettings) {
        repository.updateSettings(settings)
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.stop()
    }
}
