package com.spoelt.dicepoker.domain.model

/**
 * Contains the information necessary to create a new game of dice poker.
 *
 * @property[numberOfColumns] The number of columns that the user wants the game to have.
 * @property[numberOfPlayers] The number of players playing the game.
 */
data class GameOptions(
    val numberOfColumns: Float = 0f,
    val numberOfPlayers: Float = 0f
)
