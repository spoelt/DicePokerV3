package com.spoelt.dicepoker.ui.creategame

import com.spoelt.dicepoker.domain.model.GameOptions

/**
 * A sealed class defining all possible states of our game creation screen.
 *
 * @property[gameOptions] The current game options selected by the user.
 * @property[slidersEnabled] If true, the sliders on the game creation screen can accept changes,
 * false otherwise.
 * @property[buttonEnabled] If true, the button can be clicked, false otherwise.
 * @property[isColumnCardExpanded] True if the card displaying the number of columns has been
 * clicked, false otherwise.
 * @property[isPlayerCardExpanded] True if the card displaying the number of players has been
 * clicked, false otherwise.
 */

sealed class CreateGameViewState(
    open val gameOptions: GameOptions,
    open val slidersEnabled: Boolean = true,
    open val buttonEnabled: Boolean = false,
    open val isColumnCardExpanded: Boolean = false,
    open val isPlayerCardExpanded: Boolean = false
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
        override val gameOptions: GameOptions,
        override val isColumnCardExpanded: Boolean,
        override val isPlayerCardExpanded: Boolean
    ) : CreateGameViewState(
        gameOptions = gameOptions,
        buttonEnabled = gameOptions.numberOfColumns > 0 && gameOptions.numberOfPlayers > 0,
        isColumnCardExpanded = isColumnCardExpanded,
        isPlayerCardExpanded = isPlayerCardExpanded
    )

    /**
     * The state of the screen as the user is attempting to create a game.
     */
    data class Creating(
        override val gameOptions: GameOptions,
        override val isColumnCardExpanded: Boolean,
        override val isPlayerCardExpanded: Boolean
    ) : CreateGameViewState(
        gameOptions = gameOptions,
        slidersEnabled = false,
        isColumnCardExpanded = isColumnCardExpanded,
        isPlayerCardExpanded = isPlayerCardExpanded
    )

    /**
     * The state when the game options have successfully been chosen.
     */
    data class Created(
        override val gameOptions: GameOptions
    ) : CreateGameViewState(
        gameOptions = gameOptions,
        slidersEnabled = false
    )
}
