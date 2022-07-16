package com.spoelt.dicepoker.domain.model

import com.spoelt.dicepoker.data.model.GameEntity
import com.spoelt.dicepoker.data.model.PlayerEntity

fun Player.toPlayerEntity(gameId: String) = PlayerEntity(
    playerId = playerId,
    name = name!!,
    playedGameId = gameId,
    score = null
)

fun List<Player>.toPlayersEntityList(gameId: String) = this.map { it.toPlayerEntity(gameId) }

fun GameOptions.toGameEntity(gameId: String) = GameEntity(
    gameId = gameId,
    numberOfColumns = numberOfColumns,
    numberOfPlayers = numberOfPlayers
)
