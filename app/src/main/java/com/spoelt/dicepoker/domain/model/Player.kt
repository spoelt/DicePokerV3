package com.spoelt.dicepoker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val playerId: String,
    val name: String? = null,
    val points: List<List<Int>>? = null
) : Parcelable
