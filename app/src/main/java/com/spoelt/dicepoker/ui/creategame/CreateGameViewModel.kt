package com.spoelt.dicepoker.ui.creategame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor() : ViewModel() {

    private val _viewState: MutableStateFlow<CreateGameViewState> =
        MutableStateFlow(CreateGameViewState.Initial)
    val viewState: StateFlow<CreateGameViewState> = _viewState

    private val _navigateToNameInput: MutableSharedFlow<Boolean> = MutableSharedFlow(0)
    val navigateToNameInput: SharedFlow<Boolean> = _navigateToNameInput

    fun setSelectedColumns(columns: Int) {
        val currentGameOptions = _viewState.value.gameOptions
        val isColumnCardCurrentlyExpanded = _viewState.value.isColumnCardExpanded
        val isPlayerCardCurrentlyExpanded = _viewState.value.isPlayerCardExpanded

        _viewState.value = CreateGameViewState.Active(
            gameOptions = currentGameOptions.copy(numberOfColumns = columns),
            isColumnCardExpanded = isColumnCardCurrentlyExpanded,
            isPlayerCardExpanded = isPlayerCardCurrentlyExpanded
        )
    }

    fun setSelectedPlayers(players: Int) {
        val currentGameOptions = _viewState.value.gameOptions
        val isColumnCardCurrentlyExpanded = _viewState.value.isColumnCardExpanded
        val isPlayerCardCurrentlyExpanded = _viewState.value.isPlayerCardExpanded

        _viewState.value = CreateGameViewState.Active(
            gameOptions = currentGameOptions.copy(numberOfPlayers = players),
            isColumnCardExpanded = isColumnCardCurrentlyExpanded,
            isPlayerCardExpanded = isPlayerCardCurrentlyExpanded
        )
    }

    fun updatePlayerCardState() {
        val currentGameOptions = _viewState.value.gameOptions
        val isColumnCardCurrentlyExpanded = _viewState.value.isColumnCardExpanded
        val isPlayerCardCurrentlyExpanded = _viewState.value.isPlayerCardExpanded

        _viewState.value = CreateGameViewState.Active(
            gameOptions = when (currentGameOptions.numberOfPlayers) {
                0 -> currentGameOptions.copy(numberOfPlayers = 1)
                else -> currentGameOptions
            },
            isColumnCardExpanded = isColumnCardCurrentlyExpanded,
            isPlayerCardExpanded = !isPlayerCardCurrentlyExpanded
        )
    }

    fun updateColumnCardState() {
        val currentGameOptions = _viewState.value.gameOptions
        val isColumnCardCurrentlyExpanded = _viewState.value.isColumnCardExpanded
        val isPlayerCardCurrentlyExpanded = _viewState.value.isPlayerCardExpanded

        _viewState.value = CreateGameViewState.Active(
            gameOptions = when (currentGameOptions.numberOfColumns) {
                0 -> currentGameOptions.copy(numberOfColumns = 1)
                else -> currentGameOptions
            },
            isColumnCardExpanded = !isColumnCardCurrentlyExpanded,
            isPlayerCardExpanded = isPlayerCardCurrentlyExpanded
        )
    }

    fun navigateToNameInput() {
        val gameOptions = _viewState.value.gameOptions
        val areGameOptionsValid = gameOptions.numberOfColumns > 0 && gameOptions.numberOfPlayers > 0

        _viewState.value = CreateGameViewState.Created(
            gameOptions = gameOptions
        )

        viewModelScope.launch {
            _navigateToNameInput.emit(areGameOptionsValid)
        }
    }
}
