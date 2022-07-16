package com.spoelt.dicepoker.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "player_id")
    val playerId: String,
    @ColumnInfo(name = "played_game_id")
    val playedGameId: String,
    val name: String,
    @Embedded val score: ScoreEntity?
)
