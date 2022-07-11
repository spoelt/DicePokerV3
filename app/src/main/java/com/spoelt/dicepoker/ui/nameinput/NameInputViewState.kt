package com.spoelt.dicepoker.ui.nameinput

import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player

/**
 * A sealed class defining all possible states of the name input wizard.
 *
 * @property[gameOptions] The currently selected game options.
 * @property[players] A list of [Player] objects that contain more detailed information regarding
 * the players.
 * @property[canGoBack] If true, user can go back, false otherwise.
 * @property[canGoForward] If true, user can go forward, false otherwise.
 * @property[isStartGameButtonVisible] If true, user can start the game, false otherwise.
 * @property[currentIndex] The index of the currently selected player.
 */
sealed class NameInputViewState(
    open val gameOptions: GameOptions,
    open val players: List<Player> = emptyList(),
    open val canGoBack: Boolean = false,
    open val canGoForward: Boolean = false,
    open val isStartGameButtonVisible: Boolean = false,
    open val currentIndex: Int = 0
) {
    /**
     * The initial state of the screen with nothing selected.
     */
    object Initial : NameInputViewState(
        gameOptions = GameOptions()
    )

    /**
     * The state of the screen after the navigation argument has been loaded.
     */
    data class LoadedNavArgument(
        override val gameOptions: GameOptions,
        override val players: List<Player>
    ) : NameInputViewState(
        gameOptions = gameOptions,
        players = players
    )

    /**
     * The state of the screen as the user is entering name values.
     */
    data class Active(
        override val gameOptions: GameOptions,
        override val players: List<Player>,
        override val currentIndex: Int,
        override val canGoBack: Boolean,
        override val canGoForward: Boolean,
        override val isStartGameButtonVisible: Boolean
    ) : NameInputViewState(
        gameOptions = gameOptions,
        players = players,
        currentIndex = currentIndex,
        canGoBack = canGoBack,
        canGoForward = canGoForward,
        isStartGameButtonVisible = isStartGameButtonVisible
    )
}
