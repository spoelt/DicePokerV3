package com.spoelt.dicepoker.gamecreation.domain.model

/**
 * Contains the information necessary to create a new game of dice poker.
 *
 * @property[numberOfColumns] The number of columns that the user wants the game to have.
 * @property[numberOfPlayers] The number of players playing the game.
 * @property[isColumnCardExpanded] True if the card displaying the number of columns has been
 * clicked, false otherwise.
 * @property[isPlayerCardExpanded] True if the card displaying the number of players has been
 * clicked, false otherwise.
 */
data class GameOptions(
    val numberOfColumns: Int = 0,
    val numberOfPlayers: Int = 0,
    val isColumnCardExpanded: Boolean = false,
    val isPlayerCardExpanded: Boolean = false
)
