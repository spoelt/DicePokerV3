package com.spoelt.dicepoker.gamecreation.ui

import com.spoelt.dicepoker.gamecreation.domain.model.GameOptions

/**
 * A sealed class defining all possible states of our game creation screen.
 *
 * @property[gameOptions] The current game options selected by the user.
 * @property[slidersEnabled] If true, the sliders on the game creation screen can accept changes,
 * false otherwise.
 */
sealed class CreateGameViewState(
    open val gameOptions: GameOptions,
    open val slidersEnabled: Boolean = true,
    open val buttonEnabled: Boolean = false
) {
    /**
     * The initial state of the screen with nothing selected.
     */
    object Initial : CreateGameViewState(
        gameOptions = GameOptions()
    )

    /**
     * The state of the screen as the user is entering game option information.
     */
    data class Active(
        override val gameOptions: GameOptions
    ) : CreateGameViewState(
        gameOptions = gameOptions,
        buttonEnabled = gameOptions.numberOfColumns > 0 && gameOptions.numberOfPlayers > 0
    )

    /**
     * The state of the screen as the user is attempting to create a game.
     */
    data class Creating(
        override val gameOptions: GameOptions,
    ) : CreateGameViewState(
        gameOptions = gameOptions,
        slidersEnabled = false
    )

    /**
     * The state of the screen when there is an error creating a game.
     */
    data class CreationError(
        override val gameOptions: GameOptions,
        val errorMessage: String,
    ) : CreateGameViewState(
        gameOptions = gameOptions,
        buttonEnabled = true
    )

    /**
     * The state when the game has successfully been created.
     */
    object Created : CreateGameViewState(
        gameOptions = GameOptions(),
        slidersEnabled = false
    )
}
