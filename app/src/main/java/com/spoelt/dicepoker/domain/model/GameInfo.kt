package com.spoelt.dicepoker.domain.model

data class GameInfo(
    val numberOfColumns: Int,
    val numberOfPlayers: Int,
    val players: List<Player>
)
