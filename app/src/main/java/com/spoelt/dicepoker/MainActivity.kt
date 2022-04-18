package com.spoelt.dicepoker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.spoelt.dicepoker.core.BottomBarState
import com.spoelt.dicepoker.core.ui.NavGraphs
import com.spoelt.dicepoker.core.ui.components.DPBottomBar
import com.spoelt.dicepoker.core.ui.theme.DicePokerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()

            DicePokerTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        DPBottomBar(
                            bottomBarState = BottomBarState.VISIBLE,
                            navController = navController
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
}
