package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.data.model.ThemeMode
import com.example.ui.BottomNavTab
import com.example.ui.QuranViewModel
import com.example.ui.components.YannBottomBar
import com.example.ui.components.YannTopBar
import com.example.ui.screens.BookmarkScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuranListScreen
import com.example.ui.screens.QuranReaderScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.YannQuranTheme

class MainActivity : ComponentActivity() {

    private val viewModel: QuranViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settings by viewModel.readingSettings.collectAsState()
            val isDark = when (settings.themeMode) {
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
            }

            YannQuranTheme(darkTheme = isDark) {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: QuranViewModel) {
    val activeSurah by viewModel.activeSurah.collectAsState()
    val currentTab by viewModel.currentTab.collectAsState()

    if (activeSurah != null) {
        QuranReaderScreen(
            surah = activeSurah!!,
            viewModel = viewModel
        )
    } else {
        Scaffold(
            topBar = {
                val title = when (currentTab) {
                    BottomNavTab.BERANDA -> "YANN QURAN"
                    BottomNavTab.QURAN -> "Daftar Surah"
                    BottomNavTab.BOOKMARK -> "Bookmark & Catatan"
                    BottomNavTab.PENGATURAN -> "Pengaturan"
                }
                val subtitle = when (currentTab) {
                    BottomNavTab.BERANDA -> "Temukan Ketenangan dalam Setiap Ayat"
                    BottomNavTab.QURAN -> "114 Surah Al-Qur'an"
                    BottomNavTab.BOOKMARK -> "Tilawah & Ayat Tersimpan"
                    BottomNavTab.PENGATURAN -> "Preferensi Membaca"
                }

                YannTopBar(
                    title = title,
                    subtitle = subtitle,
                    showSearchAction = currentTab == BottomNavTab.QURAN,
                    onSearchClick = { /* Search field is already integrated on the screen */ }
                )
            },
            bottomBar = {
                YannBottomBar(
                    currentTab = currentTab,
                    onTabSelected = { viewModel.selectTab(it) }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AnimatedContent(
                    targetState = currentTab,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "tab_transition"
                ) { tab ->
                    when (tab) {
                        BottomNavTab.BERANDA -> HomeScreen(viewModel = viewModel)
                        BottomNavTab.QURAN -> QuranListScreen(viewModel = viewModel)
                        BottomNavTab.BOOKMARK -> BookmarkScreen(viewModel = viewModel)
                        BottomNavTab.PENGATURAN -> SettingsScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
