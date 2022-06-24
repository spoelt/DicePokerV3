package com.spoelt.dicepoker.domain.model

/**
 * A collection of possible results for an attempt to create a new game.
 */
sealed class CreateGameResult {
    /**
     * The attempt to create a new game was successful.
     */
    object Success : CreateGameResult()

    /**
     * This will be returned for any unknown expection when trying to create a new game.
     */
    object Failure : CreateGameResult()
}
