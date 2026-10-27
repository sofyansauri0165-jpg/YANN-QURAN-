package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.DeepForestGreen
import com.example.ui.theme.RoyalGold
import com.example.ui.theme.SoftGold

/**
 * An elegant 8-pointed Islamic Star badge for Surah & Ayah numbers.
 */
@Composable
fun IslamicNumberBadge(
    number: Int,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    badgeColor: Color = DeepForestGreen,
    accentColor: Color = RoyalGold,
    textColor: Color = SoftGold
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // Base square 1
        Box(
            modifier = Modifier
                .size(size * 0.78f)
                .background(badgeColor, RoundedCornerShape(4.dp))
                .border(1.dp, accentColor.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
        )
        // Rotated square 2 to form 8-pointed Rub el Hizb star
        Box(
            modifier = Modifier
                .size(size * 0.78f)
                .rotate(45f)
                .background(badgeColor, RoundedCornerShape(4.dp))
                .border(1.dp, accentColor.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
        )
        // Number text
        Text(
            text = "$number",
            color = textColor,
            fontSize = if (number >= 100) 11.sp else 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Beautiful Bismillah header card shown at top of Surahs.
 */
@Composable
fun BismillahHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = RoyalGold.copy(alpha = 0.4f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 16.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            fontFamily = AmiriFontFamily,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )
    }
}
