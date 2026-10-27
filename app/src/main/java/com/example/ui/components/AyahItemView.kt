package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Ayah
import com.example.data.model.ReadingSettings
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.DarkGold
import com.example.ui.theme.DarkModeActiveAyah
import com.example.ui.theme.DeepForestGreen
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.RoyalGold
import com.example.ui.theme.SoftGold
import com.example.ui.theme.SoftGreenSurface
import com.example.ui.theme.TranslationTextLight

@Composable
fun AyahItemView(
    ayah: Ayah,
    surahName: String,
    settings: ReadingSettings,
    isBookmarked: Boolean,
    isLastRead: Boolean,
    isPlayingAudio: Boolean,
    onBookmarkClick: () -> Unit,
    onMarkLastReadClick: () -> Unit,
    onPlayAudioClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val containerColor by animateColorAsState(
        targetValue = when {
            isPlayingAudio -> SoftGreenSurface
            isLastRead -> SoftGreenSurface.copy(alpha = 0.65f)
            else -> MaterialTheme.colorScheme.surface
        },
        label = "ayah_container_color"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("ayah_item_${ayah.numberInSurah}")
            .padding(horizontal = 14.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isPlayingAudio) 2.dp else 0.5.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isPlayingAudio) RoyalGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Verse Action Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Ayah badge
                IslamicNumberBadge(
                    number = ayah.numberInSurah,
                    size = 34.dp,
                    badgeColor = DeepForestGreen,
                    accentColor = RoyalGold,
                    textColor = SoftGold
                )

                // Actions
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Play audio
                    IconButton(
                        onClick = onPlayAudioClick,
                        modifier = Modifier
                            .testTag("play_audio_ayah_${ayah.numberInSurah}")
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isPlayingAudio) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (isPlayingAudio) "Jeda Audio" else "Putar Audio",
                            tint = if (isPlayingAudio) EmeraldGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Bookmark
                    IconButton(
                        onClick = onBookmarkClick,
                        modifier = Modifier
                            .testTag("bookmark_ayah_${ayah.numberInSurah}")
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Simpan Bookmark",
                            tint = if (isBookmarked) DarkGold else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Mark as Last Read
                    IconButton(
                        onClick = onMarkLastReadClick,
                        modifier = Modifier
                            .testTag("last_read_ayah_${ayah.numberInSurah}")
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isLastRead) Icons.Filled.Check else Icons.Filled.History,
                            contentDescription = "Tandai Terakhir Dibaca",
                            tint = if (isLastRead) DeepForestGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Copy
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText(
                                "Ayat Al-Qur'an",
                                "${ayah.arabicText}\n\n${ayah.latinText}\n\n\"${ayah.translationText}\" (QS. $surahName: ${ayah.numberInSurah})"
                            )
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Ayat disalin ke clipboard", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .testTag("copy_ayah_${ayah.numberInSurah}")
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "Salin Ayat",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Share
                    IconButton(
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "${ayah.arabicText}\n\n${ayah.latinText}\n\n\"${ayah.translationText}\"\n\n— QS. $surahName: ${ayah.numberInSurah}\n(Dibagikan melalui YANN QURAN)"
                                )
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Bagikan Ayat"))
                        },
                        modifier = Modifier
                            .testTag("share_ayah_${ayah.numberInSurah}")
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Bagikan Ayat",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Arabic Text
            Text(
                text = ayah.arabicText,
                fontFamily = AmiriFontFamily,
                fontSize = settings.arabicFontSize.sp,
                lineHeight = (settings.arabicFontSize * 2.0f).sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            // Latin Transliteration
            if (settings.showLatin && ayah.latinText.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = ayah.latinText,
                    fontSize = (settings.translationFontSize * 0.95f).sp,
                    lineHeight = (settings.translationFontSize * 1.4f).sp,
                    color = EmeraldGreen,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Indonesian Translation
            if (settings.showTranslation && ayah.translationText.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = ayah.translationText,
                    fontSize = settings.translationFontSize.sp,
                    lineHeight = (settings.translationFontSize * 1.5f).sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
