package com.spoelt.dicepoker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.spoelt.dicepoker.ui.navigation.BottomBarState
import com.spoelt.dicepoker.ui.navigation.DPBottomBar
import com.spoelt.dicepoker.ui.navigation.routesWithVisibleBottomBar
import com.spoelt.dicepoker.ui.theme.DicePokerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
            }

            DicePokerTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        DPBottomBar(
                            bottomBarState = updateBottomBarState(currentBackStackEntry),
                            navController = navController,
                            currentDestination = currentBackStackEntry?.destination
                        )
                    }
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController
                    )
                }
            }
        }
    }

    private fun updateBottomBarState(currentDestination: NavBackStackEntry?) =
        if (routesWithVisibleBottomBar.contains(currentDestination?.destination?.route)) {
            BottomBarState.VISIBLE
        } else {
            BottomBarState.HIDDEN
        }
}
