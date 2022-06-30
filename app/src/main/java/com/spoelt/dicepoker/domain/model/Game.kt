package com.spoelt.dicepoker.domain.model

import java.util.UUID

data class Game(
    val gameId: UUID,
    val numberOfColumns: Int,
    val numberOfPlayers: Int,
    val players: List<Player>
)
