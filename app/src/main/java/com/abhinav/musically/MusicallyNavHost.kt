package com.abhinav.musically

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abhinav.musically.Details.ARG_ARTIST_ID
import com.abhinav.musically.features.details.DetailsScreen
import com.abhinav.musically.features.details.DetailsViewModel
import com.abhinav.musically.features.details.DetailsViewModelImpl
import com.abhinav.musically.features.home.HomeScreen
import com.abhinav.musically.features.home.HomeViewModel
import com.abhinav.musically.features.home.HomeViewModelImpl

/*
* Navigation Host for both the screens.
* If in future we need to scale with more screens we should split this into each
* screens Navigation class
*/

@Composable
fun MusicallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) { backStackEntry ->
            val homeViewModel: HomeViewModel = hiltViewModel<HomeViewModelImpl>(backStackEntry)
            HomeScreen(
                onItemClick = { id ->
                    navController.navigateSingleTopTo("${Details.route}/$id")
                },
                viewModel = homeViewModel
            )
        }
        composable(
            route = Details.route + "/{" + ARG_ARTIST_ID + "}",
            arguments = listOf(
                navArgument(ARG_ARTIST_ID) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val detailsViewModel: DetailsViewModel =
                hiltViewModel<DetailsViewModelImpl>(backStackEntry)
            DetailsScreen(
                onBackClick = navController::navigateUp,
                viewModel = detailsViewModel
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
