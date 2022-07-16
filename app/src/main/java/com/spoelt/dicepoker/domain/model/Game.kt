package com.spoelt.dicepoker.domain.model

data class Game(
    val gameId: String,
    val numberOfColumns: Int,
    val numberOfPlayers: Int,
    val players: List<Player>
)
