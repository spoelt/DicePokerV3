package com.spoelt.dicepoker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Game")
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "game_id")
    val gameId: String,
    @ColumnInfo(name = "number_of_columns")
    val numberOfColumns: Int,
    @ColumnInfo(name = "number_of_players")
    val numberOfPlayers: Int
)
