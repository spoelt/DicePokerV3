package com.spoelt.dicepoker.core.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.ui.graphics.vector.ImageVector
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.core.ui.components.destinations.DirectionDestination
import com.spoelt.dicepoker.core.ui.components.destinations.HighScoresScreenDestination
import com.spoelt.dicepoker.core.ui.components.destinations.HomeScreenDestination
import com.spoelt.dicepoker.core.ui.components.destinations.RulesScreenDestination

sealed class Route(
    val destination: DirectionDestination,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    object Home : Route(
        destination = HomeScreenDestination,
        icon = Icons.Default.Home,
        label = R.string.bottom_bar_home
    )

    object HighScores : Route(
        destination = HighScoresScreenDestination,
        icon = Icons.Outlined.EmojiEvents,
        label = R.string.bottom_bar_high_scores
    )

    object Rules : Route(
        destination = RulesScreenDestination,
        icon = Icons.Default.Rule,
        label = R.string.bottom_bar_rules
    )
}

val bottomBarRoutes = listOf(
    Route.Home,
    Route.HighScores,
    Route.Rules
)
