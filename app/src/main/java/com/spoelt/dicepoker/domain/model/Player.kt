package com.spoelt.dicepoker.domain.model

data class Player(
    val playerId: String,
    val name: String? = null,
    val points: List<List<Int>>? = null
)
