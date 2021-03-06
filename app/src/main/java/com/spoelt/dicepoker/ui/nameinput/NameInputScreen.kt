package com.spoelt.dicepoker.ui.nameinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.spoelt.dicepoker.destinations.GameScreenDestination
import com.spoelt.dicepoker.domain.model.CreateGameResult.Failure
import com.spoelt.dicepoker.domain.model.CreateGameResult.Success
import com.spoelt.dicepoker.domain.model.GameOptions
import timber.log.Timber

@Destination(navArgsDelegate = GameOptions::class)
@Composable
fun NameInputScreen(
    navigator: DestinationsNavigator,
    viewModel: NameInputViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startGame.collect { result ->
            when (result) {
                Failure -> Timber.e("Couldn't start game")
                is Success -> {
                    navigator.apply {
                        popBackStack()
                        navigate(GameScreenDestination(navArgs = result.gameNavArg))
                    }
                }
            }
        }
    }

    NameInputContent(
        viewState = viewState,
        onCloseClicked = navigator::popBackStack,
        onPlayerNameChanged = viewModel::updatePlayerName,
        onGoBackClicked = viewModel::updateIndex,
        onGoForwardClicked = viewModel::updateIndex,
        onStartGame = viewModel::startGame
    )
}
