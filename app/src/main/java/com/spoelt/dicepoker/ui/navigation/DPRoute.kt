package com.spoelt.dicepoker.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.destinations.CreateGameScreenDestination
import com.spoelt.dicepoker.destinations.HighScoresScreenDestination
import com.spoelt.dicepoker.destinations.HomeScreenDestination
import com.spoelt.dicepoker.destinations.RulesScreenDestination

sealed class DPRoute(
    val destination: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    object Home : DPRoute(
        destination = HomeScreenDestination,
        icon = Icons.Default.Home,
        label = R.string.bottom_bar_home
    )

    object HighScores : DPRoute(
        destination = HighScoresScreenDestination,
        icon = Icons.Outlined.EmojiEvents,
        label = R.string.bottom_bar_high_scores
    )

    object Rules : DPRoute(
        destination = RulesScreenDestination,
        icon = Icons.Default.Rule,
        label = R.string.bottom_bar_rules
    )
}

val bottomBarRoutes = listOf(
    DPRoute.Home,
    DPRoute.HighScores,
    DPRoute.Rules
)

val routesWithVisibleBottomBar = listOf(
    HomeScreenDestination.route,
    HighScoresScreenDestination.route,
    RulesScreenDestination.route,
    CreateGameScreenDestination.route
)
