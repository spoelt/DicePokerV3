package com.spoelt.dicepoker.data.model

import androidx.room.ColumnInfo

data class ScoreEntity(
    @ColumnInfo(name = "score_column_one")
    val scoreColumnOne: Int,
    @ColumnInfo(name = "has_won_column_one")
    val hasWonColumnOne: Boolean,
    @ColumnInfo(name = "score_column_two")
    val scoreColumnTwo: Int?,
    @ColumnInfo(name = "has_won_column_two")
    val hasWonColumnTwo: Boolean?,
    @ColumnInfo(name = "score_column_three")
    val scoreColumnThree: Int?,
    @ColumnInfo(name = "has_won_column_three")
    val hasWonColumnThree: Boolean?
)
