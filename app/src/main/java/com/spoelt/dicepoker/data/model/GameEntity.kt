package com.spoelt.dicepoker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "Game")
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    val gameId: UUID,
    val numberOfColumns: Int,
    val numberOfPlayers: Int
)
