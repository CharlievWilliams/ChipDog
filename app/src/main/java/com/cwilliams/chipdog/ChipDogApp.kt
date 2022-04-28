package com.cwilliams.chipdog

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cwilliams.chipdog.ui.theme.ChipDogTheme
import com.cwilliams.chipdog.view.FirstView
import com.cwilliams.chipdog.view.NavigationBar
import com.cwilliams.chipdog.viewModel.FirstViewModel

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun ChipDogApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    ChipDogTheme {
        Scaffold(
            topBar = {
                NavigationBar(
                    screen = toScreen(navBackStackEntry),
                    popBackStack = { navController.popBackStack() }
                )
            }
        ) { padding ->
            // TODO: Add animated nav host
            NavHost(
                navController = navController,
                startDestination = Screen.FirstView.route,
                modifier = Modifier.padding(paddingValues = padding)
            ) {
                composable(route = Screen.FirstView.route) {
                    val firstViewModel = hiltViewModel<FirstViewModel>()
                    FirstView(
                        viewModel = firstViewModel,
                        navigateToNextScreen = {
                            // TODO: Navigate forward
                        }
                    )
                }
            }
        }
    }
}