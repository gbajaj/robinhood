package com.gauravbajaj.interviewready.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gauravbajaj.interviewready.ui.screens.DetailsScreen
import com.gauravbajaj.interviewready.ui.screens.HomeScreen

@Composable
fun InterviewReadyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, onItemClick = { user ->
                navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
                navController.navigate(Screen.Details.route)
            })
        }

        composable(
            route = Screen.Details.route,
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
            )
        }
    }
}
