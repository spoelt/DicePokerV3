package com.spoelt.dicepoker.data.local

import com.spoelt.dicepoker.data.model.GameEntity
import com.spoelt.dicepoker.data.model.PlayerEntity
import com.spoelt.dicepoker.data.model.ScoreEntity
import java.util.UUID

val gameId: UUID = UUID.randomUUID()

val testGame = GameEntity(
    gameId = gameId,
    numberOfColumns = 1,
    numberOfPlayers = 2
)

val testPlayers = (1..4).map { i ->
    PlayerEntity(
        playerId = i,
        playedGameId = gameId,
        name = "Player $i",
        score = ScoreEntity(
            scoreColumnOne = i * i,
            hasWonColumnOne = i * i % 2 == 0,
            scoreColumnTwo = null,
            hasWonColumnTwo = null,
            scoreColumnThree = null,
            hasWonColumnThree = null
        )
    )
}

val testGames = (1..4).map { i ->
    GameEntity(
        gameId = UUID.randomUUID(),
        numberOfColumns = i,
        numberOfPlayers = i
    )
}
