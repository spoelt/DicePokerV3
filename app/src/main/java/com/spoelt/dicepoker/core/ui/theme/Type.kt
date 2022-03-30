package com.spoelt.dicepoker.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.spoelt.dicepoker.R

private val UbuntuMedium = FontFamily(Font(R.font.ubuntu_medium))
private val UbuntuLight = FontFamily(Font(R.font.ubuntu_light))
private val UbuntuRegular = FontFamily(Font(R.font.ubuntu_regular))

val typography = Typography(
    h1 = TextStyle(
        fontFamily = UbuntuMedium,
        fontSize = 36.sp
    ),
    h2 = TextStyle(
        fontFamily = UbuntuMedium,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = UbuntuRegular,
        fontSize = 22.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = UbuntuRegular,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = UbuntuRegular,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = UbuntuMedium,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = UbuntuLight,
        fontSize = 12.sp
    )
)
