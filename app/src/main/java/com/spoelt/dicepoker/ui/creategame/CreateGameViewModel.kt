package com.spoelt.dicepoker.ui.creategame

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor() : ViewModel() {

    private val _viewState: MutableStateFlow<CreateGameViewState> =
        MutableStateFlow(CreateGameViewState.Initial)
    val viewState: StateFlow<CreateGameViewState> = _viewState

    fun setSelectedColumns(columns: Float) {
        val currentGameOptions = _viewState.value.gameOptions
        val isColumnCardCurrentlyExpanded = _viewState.value.isColumnCardExpanded
        val isPlayerCardCurrentlyExpanded = _viewState.value.isPlayerCardExpanded

        _viewState.value = CreateGameViewState.Active(
            gameOptions = currentGameOptions.copy(numberOfColumns = columns),
            isColumnCardExpanded = isColumnCardCurrentlyExpanded,
            isPlayerCardExpanded = isPlayerCardCurrentlyExpanded
        )
    }

    fun setSelectedPlayers(players: Float) {
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
                0f -> currentGameOptions.copy(numberOfPlayers = 1f)
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
                0f -> currentGameOptions.copy(numberOfColumns = 1f)
                else -> currentGameOptions
            },
            isColumnCardExpanded = !isColumnCardCurrentlyExpanded,
            isPlayerCardExpanded = isPlayerCardCurrentlyExpanded
        )
    }
}
