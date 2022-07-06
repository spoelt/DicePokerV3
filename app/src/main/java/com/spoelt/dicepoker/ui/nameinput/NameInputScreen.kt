package com.spoelt.dicepoker.ui.nameinput

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.spoelt.dicepoker.domain.model.GameOptions

@Destination(navArgsDelegate = GameOptions::class)
@Composable
fun NameInputScreen(
    viewModel: NameInputViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    Text(text = viewState.gameOptions.numberOfColumns.toString())
}
