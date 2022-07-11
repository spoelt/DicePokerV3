package com.spoelt.dicepoker.ui.navigation

import android.content.res.Configuration
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.navigation.navigate
import com.spoelt.dicepoker.ui.theme.DicePokerTheme

@Composable
fun DPBottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (routesWithVisibleBottomBar.contains(currentRoute)) {
        BottomNavigation {
            bottomBarRoutes.forEach { b ->
                BottomNavigationItem(
                    selected = currentRoute == b.destination.route,
                    onClick = {
                        navController.navigate(b.destination) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = b.icon,
                            contentDescription = stringResource(b.label)
                        )
                    },
                    label = { Text(text = stringResource(b.label)) },
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
            navController = rememberNavController()
        )
    }
}
