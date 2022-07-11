package com.spoelt.dicepoker.ui.nameinput

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoelt.dicepoker.domain.model.Game
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player
import com.spoelt.dicepoker.domain.model.PlayerNameDirection
import com.spoelt.dicepoker.domain.model.PlayerNameDirection.BACK
import com.spoelt.dicepoker.domain.model.PlayerNameDirection.FORWARD
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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState: MutableStateFlow<NameInputViewState> =
        MutableStateFlow(NameInputViewState.Initial)
    val viewState: StateFlow<NameInputViewState> = _viewState

    private val _startGame: MutableSharedFlow<Game> = MutableSharedFlow()
    val startGame: SharedFlow<Game> = _startGame

    init {
        val gameOptions = savedStateHandle.navArgs<GameOptions>()

        _viewState.value = NameInputViewState.LoadedNavArgument(
            gameOptions = gameOptions,
            players = createPlayerList(gameOptions.numberOfPlayers)
        )
    }

    private fun createPlayerList(numberOfPlayers: Int) = (1..numberOfPlayers).map { i ->
        Player(playerId = i)
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
            val gameId = UUID.randomUUID()
            val options = _viewState.value.gameOptions
            val game = Game(
                gameId = gameId,
                numberOfColumns = options.numberOfColumns,
                numberOfPlayers = options.numberOfPlayers,
                players = updatePlayedGameId(
                    players = _viewState.value.players,
                    id = gameId
                )
            )
            _startGame.emit(game)
        }
    }

    private fun updatePlayedGameId(players: List<Player>, id: UUID) = players.map { player ->
        player.copy(playedGameId = id)
    }
}
