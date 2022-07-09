package com.spoelt.dicepoker.ui.creategame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.spoelt.dicepoker.destinations.NameInputScreenDestination

@Destination
@Composable
fun CreateGameScreen(
    navigator: DestinationsNavigator,
    viewModel: CreateGameViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigateToNameInput.collect { shouldNavigate ->
            if (shouldNavigate) {
                navigator.navigate(NameInputScreenDestination(navArgs = viewState.gameOptions))
            }
        }
    }

    CreateGameContent(
        viewState = viewState,
        onColumnsSelected = viewModel::setSelectedColumns,
        onPlayersSelected = viewModel::setSelectedPlayers,
        onNextClicked = viewModel::navigateToNameInput,
        onPlayerCardClicked = viewModel::updatePlayerCardState,
        onColumnCardClicked = viewModel::updateColumnCardState
    )
}
