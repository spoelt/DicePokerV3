package com.spoelt.dicepoker.domain.model

import com.spoelt.dicepoker.data.model.GameEntity
import com.spoelt.dicepoker.data.model.GameWithPlayers
import com.spoelt.dicepoker.data.model.PlayerEntity
import com.spoelt.dicepoker.data.model.ScoreEntity

fun ScoreEntity.toScore() = Score(
    scoreColumnOne = scoreColumnOne,
    hasWonColumnOne = hasWonColumnOne,
    scoreColumnTwo = scoreColumnTwo,
    hasWonColumnTwo = hasWonColumnTwo,
    scoreColumnThree = scoreColumnThree,
    hasWonColumnThree = hasWonColumnThree
)

fun Score.toScoreEntity() = ScoreEntity(
    scoreColumnOne = scoreColumnOne,
    hasWonColumnOne = hasWonColumnOne,
    scoreColumnTwo = scoreColumnTwo,
    hasWonColumnTwo = hasWonColumnTwo,
    scoreColumnThree = scoreColumnThree,
    hasWonColumnThree = hasWonColumnThree
)

fun PlayerEntity.toPlayer() = Player(
    playerId = playerId,
    playedGameId = playedGameId,
    name = name,
    score = score.toScore()
)

fun Player.toPlayerEntity() = PlayerEntity(
    playerId = playerId,
    playedGameId = playedGameId!!,
    name = name!!,
    score = score!!.toScoreEntity()
)

fun List<PlayerEntity>.toPlayersList() = this.map { it.toPlayer() }

fun List<Player>.toPlayersEntityList() = this.map { it.toPlayerEntity() }

fun GameWithPlayers.toGame() = Game(
    gameId = game.gameId,
    numberOfPlayers = game.numberOfPlayers,
    numberOfColumns = game.numberOfColumns,
    players = players.toPlayersList()
)

fun List<GameWithPlayers>.toGameList() = this.map { it.toGame() }

fun Game.toGameEntity() = GameEntity(
    gameId, numberOfColumns, numberOfPlayers
)

fun Game.toPlayersEntityList() = players.toPlayersEntityList()
