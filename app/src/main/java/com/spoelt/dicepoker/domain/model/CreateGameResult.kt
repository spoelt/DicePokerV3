package com.spoelt.dicepoker.domain.model

/**
 * A collection of possible results for an attempt to create a new game.
 */
sealed class CreateGameResult {
    /**
     * The attempt to create a new game was successful.
     */
    data class Success(val gameNavArg: GameNavArg) : CreateGameResult()

    /**
     * This will be returned for any unknown exception when trying to create a new game.
     */
    object Failure : CreateGameResult()
}
