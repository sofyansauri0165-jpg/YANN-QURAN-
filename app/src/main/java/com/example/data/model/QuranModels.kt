package com.example.data.model

data class Surah(
    val number: Int,
    val name: String,
    val arabicName: String,
    val translation: String,
    val numberOfAyahs: Int,
    val revelationType: String, // "Makkiyah" or "Madaniyah"
    val revelationOrder: Int = 1,
    val juzStart: Int = 1
)

data class Ayah(
    val numberInSurah: Int,
    val surahNumber: Int,
    val arabicText: String,
    val latinText: String = "",
    val translationText: String = "",
    val audioUrl: String = "",
    val juz: Int = 1
)

data class Bookmark(
    val id: String,
    val surahNumber: Int,
    val surahName: String,
    val ayahNumber: Int,
    val arabicSnippet: String,
    val translationSnippet: String,
    val createdAt: Long = System.currentTimeMillis(),
    val note: String = ""
)

data class LastRead(
    val surahNumber: Int = 18,
    val surahName: String = "Al-Kahf",
    val ayahNumber: Int = 10,
    val juzNumber: Int = 15,
    val timestamp: Long = System.currentTimeMillis()
)

data class ReadingSettings(
    val arabicFontSize: Float = 28f, // sp
    val translationFontSize: Float = 15f, // sp
    val showLatin: Boolean = true,
    val showTranslation: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val selectedReciter: String = "Mishary Rashid Alafasy"
)

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

data class PrayerSchedule(
    val fajr: String = "04:28",
    val sunrise: String = "05:46",
    val dhuhr: String = "11:54",
    val asr: String = "15:08",
    val maghrib: String = "17:59",
    val isha: String = "19:10",
    val cityName: String = "Jakarta & Sekitarnya"
)
