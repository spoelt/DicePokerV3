package com.spoelt.dicepoker.domain.model

import java.util.UUID

data class Player(
    val playerId: Int,
    val playedGameId: UUID? = null,
    val name: String? = null,
    val score: Score? = null
)
