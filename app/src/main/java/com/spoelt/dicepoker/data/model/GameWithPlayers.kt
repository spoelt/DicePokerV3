package com.spoelt.dicepoker.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithPlayers(
    @Embedded val game: GameEntity,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "playedGameId"
    )
    val players: List<PlayerEntity>
)
