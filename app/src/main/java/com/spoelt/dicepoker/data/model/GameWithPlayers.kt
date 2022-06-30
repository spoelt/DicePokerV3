package com.spoelt.dicepoker.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithPlayers(
    @Embedded val game: GameEntity,
    @Relation(
        parentColumn = "game_id",
        entityColumn = "played_game_id"
    )
    val players: List<PlayerEntity>
)
