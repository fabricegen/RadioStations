package com.radiostations.core.design.system

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors()
}

data class ExtendedColors(
    val purple80: Color = ColorTokens.Purple80,
    val purpleGrey80: Color = ColorTokens.PurpleGrey80,
    val pink80: Color = ColorTokens.Pink80,
    val purple40: Color = ColorTokens.Purple40,
    val purpleGrey40: Color = ColorTokens.PurpleGrey40,
    val pink40: Color = ColorTokens.Pink40,
    val blue: Color = ColorTokens.Blue
    )