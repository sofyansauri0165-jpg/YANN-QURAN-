package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Surah
import com.example.ui.QuranViewModel
import com.example.ui.components.AyahItemView
import com.example.ui.components.BismillahHeader
import com.example.ui.components.YannTopBar
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.DeepForestGreen
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.RoyalGold
import com.example.ui.theme.SoftGold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranReaderScreen(
    surah: Surah,
    viewModel: QuranViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val ayahs by viewModel.activeAyahs.collectAsState()
    val isLoading by viewModel.isLoadingAyahs.collectAsState()
    val lastRead by viewModel.lastRead.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val settings by viewModel.readingSettings.collectAsState()
    val isAudioPlaying by viewModel.isAudioPlaying.collectAsState()
    val currentPlayingAyah by viewModel.currentPlayingAyah.collectAsState()
    val targetScrollAyah by viewModel.targetScrollAyah.collectAsState()

    val listState = rememberLazyListState()
    var showSettingsSheet by remember { mutableStateOf(false) }

    // Intercept back key to return to previous tab
    BackHandler {
        viewModel.closeReader()
    }

    // Auto-scroll to target ayah when opened
    LaunchedEffect(ayahs, targetScrollAyah) {
        if (ayahs.isNotEmpty() && targetScrollAyah > 1) {
            val index = ayahs.indexOfFirst { it.numberInSurah == targetScrollAyah }
            if (index >= 0) {
                // Offset for header
                val targetIndex = if (surah.number != 9) index + 1 else index
                listState.animateScrollToItem(targetIndex)
            }
        }
    }

    // Auto-scroll when audio advances
    LaunchedEffect(currentPlayingAyah) {
        currentPlayingAyah?.let { playingAyahNum ->
            val index = ayahs.indexOfFirst { it.numberInSurah == playingAyahNum }
            if (index >= 0) {
                val targetIndex = if (surah.number != 9) index + 1 else index
                listState.animateScrollToItem(targetIndex)
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                YannTopBar(
                    title = surah.name,
                    subtitle = "${surah.translation} • ${surah.revelationType} • ${surah.numberOfAyahs} Ayat",
                    arabicTitle = surah.arabicName,
                    showBackButton = true,
                    onBackClick = { viewModel.closeReader() }
                )

                // Quick Tool Action Bar
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Juz ${surah.juzStart}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepForestGreen
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Quick text resize button
                            IconButton(
                                onClick = { showSettingsSheet = true },
                                modifier = Modifier
                                    .testTag("reader_settings_button")
                                    .size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.FormatSize,
                                    contentDescription = "Ukuran Huruf",
                                    tint = DeepForestGreen
                                )
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            // Audio Floating Bar
            AnimatedVisibility(
                visible = isAudioPlaying || currentPlayingAyah != null,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Surface(
                    color = DeepForestGreen,
                    shadowElevation = 8.dp,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(RoyalGold, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PlayArrow,
                                    contentDescription = null,
                                    tint = DeepForestGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Memutar Ayat ${currentPlayingAyah ?: 1}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Surah ${surah.name} • ${settings.selectedReciter}",
                                    color = SoftGold,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = { viewModel.togglePlayPause() },
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    imageVector = if (isAudioPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                    contentDescription = if (isAudioPlaying) "Jeda" else "Lanjutkan",
                                    tint = Color.White
                                )
                            }

                            IconButton(
                                onClick = { viewModel.stopAudio() },
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Stop,
                                    contentDescription = "Berhenti",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = DeepForestGreen,
                        strokeWidth = 3.dp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Memuat ayat Al-Qur'an...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 40.dp)
                ) {
                    // Bismillah Header (except At-Taubah surah 9)
                    if (surah.number != 9) {
                        item {
                            BismillahHeader(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                            )
                        }
                    }

                    // Verses
                    items(
                        items = ayahs,
                        key = { it.numberInSurah }
                    ) { ayah ->
                        val isBookmarked = bookmarks.any {
                            it.surahNumber == ayah.surahNumber && it.ayahNumber == ayah.numberInSurah
                        }
                        val isLastRead = lastRead.surahNumber == ayah.surahNumber && lastRead.ayahNumber == ayah.numberInSurah
                        val isPlaying = isAudioPlaying && currentPlayingAyah == ayah.numberInSurah

                        AyahItemView(
                            ayah = ayah,
                            surahName = surah.name,
                            settings = settings,
                            isBookmarked = isBookmarked,
                            isLastRead = isLastRead,
                            isPlayingAudio = isPlaying,
                            onBookmarkClick = { viewModel.toggleBookmark(ayah, surah.name) },
                            onMarkLastReadClick = { viewModel.markAsLastRead(ayah.surahNumber, ayah.numberInSurah) },
                            onPlayAudioClick = {
                                if (isPlaying) {
                                    viewModel.togglePlayPause()
                                } else {
                                    viewModel.playAyahAudio(ayah)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    // Settings & Typography Bottom Sheet
    if (showSettingsSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSettingsSheet = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 36.dp)
            ) {
                Text(
                    text = "Pengaturan Bacaan",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Arabic Font Size
                Text(
                    text = "Ukuran Huruf Arab: ${settings.arabicFontSize.toInt()} sp",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Slider(
                    value = settings.arabicFontSize,
                    onValueChange = { viewModel.updateSettings(settings.copy(arabicFontSize = it)) },
                    valueRange = 22f..42f,
                    colors = SliderDefaults.colors(
                        thumbColor = DeepForestGreen,
                        activeTrackColor = EmeraldGreen
                    ),
                    modifier = Modifier.testTag("slider_arabic_font_size")
                )

                // Arabic preview
                Text(
                    text = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
                    fontFamily = AmiriFontFamily,
                    fontSize = settings.arabicFontSize.sp,
                    lineHeight = (settings.arabicFontSize * 1.8f).sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Translation Font Size
                Text(
                    text = "Ukuran Huruf Terjemahan: ${settings.translationFontSize.toInt()} sp",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Slider(
                    value = settings.translationFontSize,
                    onValueChange = { viewModel.updateSettings(settings.copy(translationFontSize = it)) },
                    valueRange = 12f..22f,
                    colors = SliderDefaults.colors(
                        thumbColor = DeepForestGreen,
                        activeTrackColor = EmeraldGreen
                    ),
                    modifier = Modifier.testTag("slider_translation_font_size")
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Toggle Latin
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Tampilkan Teks Latin (Transliterasi)",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Membantu membaca bagi yang belajar",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Switch(
                        checked = settings.showLatin,
                        onCheckedChange = { viewModel.updateSettings(settings.copy(showLatin = it)) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = EmeraldGreen
                        ),
                        modifier = Modifier.testTag("switch_show_latin")
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Toggle Translation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Tampilkan Terjemahan Bahasa Indonesia",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Memahami makna dan hikmah ayat",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Switch(
                        checked = settings.showTranslation,
                        onCheckedChange = { viewModel.updateSettings(settings.copy(showTranslation = it)) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = EmeraldGreen
                        ),
                        modifier = Modifier.testTag("switch_show_translation")
                    )
                }
            }
        }
    }
}
