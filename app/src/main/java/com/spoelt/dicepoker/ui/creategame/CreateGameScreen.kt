package com.spoelt.dicepoker.ui.creategame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination
@Composable
fun CreateGameScreen(
    viewModel: CreateGameViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    CreateGameContent(
        viewState = viewState,
        onColumnsSelected = viewModel::setSelectedColumns,
        onPlayersSelected = viewModel::setSelectedPlayers,
        onNextClicked = {
            Timber.d("onNextClicked")
        },
        onPlayerCardClicked = viewModel::updatePlayerCardState,
        onColumnCardClicked = viewModel::updateColumnCardState
    )
}
