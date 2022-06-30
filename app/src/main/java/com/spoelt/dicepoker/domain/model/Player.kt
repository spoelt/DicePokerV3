package com.spoelt.dicepoker.domain.model

import java.util.UUID

data class Player(
    val playerId: Int,
    val playedGameId: UUID,
    val name: String,
    val score: Score
)
