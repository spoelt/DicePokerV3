package com.spoelt.dicepoker.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.spoelt.dicepoker.R

private val UbuntuMedium = Font(R.font.ubuntu_medium, FontWeight.Medium)
private val UbuntuLight = Font(R.font.ubuntu_light, FontWeight.Light)
private val UbuntuRegular = Font(R.font.ubuntu_regular, FontWeight.Normal)
private val UbuntuBold = Font(R.font.ubuntu_bold, FontWeight.Bold)

private val UbuntuFamily = FontFamily(
    UbuntuMedium,
    UbuntuLight,
    UbuntuRegular,
    UbuntuBold
)

val typography = Typography(
    defaultFontFamily = UbuntuFamily
)
