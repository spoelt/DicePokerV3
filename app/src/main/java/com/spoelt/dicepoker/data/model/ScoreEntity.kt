package com.spoelt.dicepoker.data.model

data class ScoreEntity(
    val scoreColumnOne: Int,
    val hasWonColumnOne: Boolean,
    val scoreColumnTwo: Int?,
    val hasWonColumnTwo: Boolean?,
    val scoreColumnThree: Int?,
    val hasWonColumnThree: Boolean?
)
