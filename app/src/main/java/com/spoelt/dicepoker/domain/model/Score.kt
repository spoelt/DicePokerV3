package com.spoelt.dicepoker.domain.model

data class Score(
    val scoreColumnOne: Int,
    val hasWonColumnOne: Boolean,
    val scoreColumnTwo: Int?,
    val hasWonColumnTwo: Boolean?,
    val scoreColumnThree: Int?,
    val hasWonColumnThree: Boolean?
)
