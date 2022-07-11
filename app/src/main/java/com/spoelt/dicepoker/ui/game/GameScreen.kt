package com.spoelt.dicepoker.ui.game

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination//(navArgsDelegate = Game::class)
@Composable
fun GameScreen() {
    Text("Game")
}