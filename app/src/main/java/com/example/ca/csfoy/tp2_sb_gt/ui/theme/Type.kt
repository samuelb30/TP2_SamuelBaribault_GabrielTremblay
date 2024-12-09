package com.example.ca.csfoy.tp2_sb_gt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ca.csfoy.tp2_sb_gt.R

val appFont = FontFamily(Font(R.font.parkinsans_font))
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = appFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = appFont,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = appFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)