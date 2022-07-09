package com.spoelt.dicepoker.domain.model

import kotlinx.serialization.Serializable

/**
 * Contains the information necessary to create a new game of dice poker.
 *
 * @property[numberOfColumns] The number of columns that the user wants the game to have.
 * @property[numberOfPlayers] The number of players playing the game.
 */
@Serializable
data class GameOptions(
    val numberOfColumns: Int = 0,
    val numberOfPlayers: Int = 0
)
