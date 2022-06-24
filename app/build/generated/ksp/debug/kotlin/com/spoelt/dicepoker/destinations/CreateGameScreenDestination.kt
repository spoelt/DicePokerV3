package com.spoelt.dicepoker.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.spoelt.dicepoker.ui.creategame.CreateGameScreen

object CreateGameScreenDestination : DirectionDestination {
         
    operator fun invoke() = this
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "create_game_screen"

    override val route = baseRoute
    
    @Composable
    override fun DestinationScope<Unit>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<Unit>.() -> Unit
    ) {
		CreateGameScreen()
    }
    
}