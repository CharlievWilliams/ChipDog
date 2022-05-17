package com.cwilliams.chipdog

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cwilliams.chipdog.view.component.ChipTopAppBar
import com.cwilliams.chipdog.view.screen.BreedImageScreen
import com.cwilliams.chipdog.view.screen.BreedListScreen
import com.cwilliams.chipdog.viewModel.BreedImageViewModel
import com.cwilliams.chipdog.viewModel.BreedListViewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun ChipDogApp(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val largeTopAppBarScrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    // Responsive Booleans
    val showTopAppBar = windowSizeClass.heightSizeClass != WindowHeightSizeClass.Compact

    Scaffold(
        topBar = {
            if (showTopAppBar) {
                ChipTopAppBar(
                    screen = toScreen(navBackStackEntry),
                    popBackStack = { navController.popBackStack() },
                    scrollBehavior = largeTopAppBarScrollBehavior
                )
            }
        },
        modifier = if (toScreen(navBackStackEntry).screenState?.showLargeAppBar == true) {
            Modifier.nestedScroll(largeTopAppBarScrollBehavior.nestedScrollConnection)
        } else {
            Modifier
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.BreedListScreen.route,
            modifier = Modifier.padding(paddingValues = padding)
        ) {
            composable(route = Screen.BreedListScreen.route) {
                val breedListViewModel = hiltViewModel<BreedListViewModel>()
                BreedListScreen(
                    viewModel = breedListViewModel,
                    navigateToNextScreen = { breed ->
                        navController.navigate("breed_image_screen/${breed}")
                    }
                )
            }
            composable(
                route = Screen.BreedImageScreen.route,
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { backStackEntry ->
                val breedImageViewModel = hiltViewModel<BreedImageViewModel>()
                BreedImageScreen(
                    viewModel = breedImageViewModel,
                    name = backStackEntry.arguments?.getString("name")
                )
            }
        }
    }
}