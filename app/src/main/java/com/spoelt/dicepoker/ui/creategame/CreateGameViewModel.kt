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

    @Suppress("UnusedPrivateMember")
    fun setSelectedColumns(columns: Float) {
        // TODO
    }

    @Suppress("UnusedPrivateMember")
    fun setSelectedPlayers(players: Float) {
        // TODO
    }

    fun createGame() {
        // TODO
    }
}
