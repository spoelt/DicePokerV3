package com.spoelt.dicepoker.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val playerId: Int,
    val playedGameId: UUID,
    val name: String,
    @Embedded val score: ScoreEntity
)
