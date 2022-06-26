package com.spoelt.dicepoker.ui.navigation

import android.content.res.Configuration
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.spoelt.dicepoker.navDestination
import com.spoelt.dicepoker.ui.theme.DicePokerTheme

@Composable
fun DPBottomBar(
    bottomBarState: BottomBarState,
    navController: NavController,
) {
    if (bottomBarState == BottomBarState.VISIBLE) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.navDestination

        BottomNavigation {
            bottomBarRoutes.forEach { b ->
                BottomNavigationItem(
                    selected = currentDestination == b.destination,
                    onClick = {
                        navController.navigateTo(b.destination) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            b.icon,
                            contentDescription = stringResource(b.label)
                        )
                    },
                    label = { Text(stringResource(b.label)) },
                )
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
@Suppress("UnusedPrivateMember")
private fun DPBottomBarPreview() {
    DicePokerTheme {
        DPBottomBar(
            bottomBarState = BottomBarState.VISIBLE,
            navController = rememberNavController()
        )
    }
}