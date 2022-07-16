package com.spoelt.dicepoker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameInfo(
    val numberOfColumns: Int,
    val numberOfPlayers: Int,
    val players: List<Player>
) : Parcelable
