package com.spoelt.dicepoker.ui.nameinput

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoelt.dicepoker.domain.model.CreateGameResult
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player
import com.spoelt.dicepoker.domain.model.PlayerNameDirection
import com.spoelt.dicepoker.domain.model.PlayerNameDirection.BACK
import com.spoelt.dicepoker.domain.model.PlayerNameDirection.FORWARD
import com.spoelt.dicepoker.domain.usecase.CreateGameUseCase
import com.spoelt.dicepoker.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NameInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createGameUseCase: CreateGameUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<NameInputViewState> =
        MutableStateFlow(NameInputViewState.Initial)
    val viewState: StateFlow<NameInputViewState> = _viewState

    private val _startGame: MutableSharedFlow<CreateGameResult> = MutableSharedFlow()
    val startGame: SharedFlow<CreateGameResult> = _startGame

    init {
        val gameOptions = savedStateHandle.navArgs<GameOptions>()

        _viewState.value = NameInputViewState.LoadedNavArgument(
            gameOptions = gameOptions,
            players = createPlayerList(gameOptions.numberOfPlayers)
        )
    }

    private fun createPlayerList(numberOfPlayers: Int) = (1..numberOfPlayers).map {
        Player(playerId = UUID.randomUUID().toString())
    }

    fun updateIndex(direction: PlayerNameDirection) {
        val currentViewState = _viewState.value
        val currentIndex = currentViewState.currentIndex
        val players = currentViewState.players
        val updatedIndex = when (direction) {
            FORWARD -> currentIndex.plus(1)
            BACK -> currentIndex.minus(1)
        }
        val isNameValid = isNameValid(players[updatedIndex].name)

        _viewState.value = NameInputViewState.Active(
            gameOptions = currentViewState.gameOptions,
            players = players,
            currentIndex = updatedIndex,
            canGoBack = isNotFirstPlayer(updatedIndex),
            canGoForward = isNameValid && isNotLastPlayer(
                index = updatedIndex,
                last = players.size.minus(1)
            ),
            isStartGameButtonVisible = isNameValid && !isNotLastPlayer(
                index = updatedIndex,
                last = players.size.minus(1)
            )
        )
    }

    private fun isNotFirstPlayer(index: Int) = index > 0

    private fun isNotLastPlayer(index: Int, last: Int) = index < last

    private fun isNameValid(name: String?) = name?.trim()?.isNotBlank() ?: false

    fun updatePlayerName(name: String) {
        val currentViewState = _viewState.value
        val currentIndex = currentViewState.currentIndex
        val players = currentViewState.players
        val playerToBeUpdated = players[currentIndex].copy(name = name)
        val isNameValid = isNameValid(name)

        _viewState.value = NameInputViewState.Active(
            gameOptions = currentViewState.gameOptions,
            players = players.mapIndexed { index, player ->
                if (index == currentIndex) playerToBeUpdated else player
            },
            currentIndex = currentIndex,
            canGoBack = currentIndex > 0,
            canGoForward = isNameValid && isNotLastPlayer(
                index = currentIndex,
                last = players.size.minus(1)
            ),
            isStartGameButtonVisible = isNameValid && !isNotLastPlayer(
                index = currentIndex,
                last = players.size.minus(1)
            )
        )
    }

    fun startGame() {
        viewModelScope.launch {
            val result = createGameUseCase.invoke(
                gameOptions = _viewState.value.gameOptions,
                players = _viewState.value.players
            )
            _startGame.emit(result)
        }
    }
}
