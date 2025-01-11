package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CategoryDetailPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MainPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel

// Composable function for setting up the main navigation graph.
// Defines the navigation routes for the application, including the Main Page, Category Page, Category Detail Page, and Movie Detail Page.

@Composable
fun MainScreenNavigation(mainPageViewModel: MainPageViewModel,
                         movieDetailPageViewModel: MovieDetailPageViewModel,
                         categoryDetailPageViewModel: CategoryDetailPageViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainPage"){

        // Main Page: The home screen of the application
        composable("mainPage"){
            MainPage(mainPageViewModel = mainPageViewModel, navController = navController)
        }

        // Category Page: Displays a list of categories for navigating to category details.
        composable("categoryPage"){

            CategoryPage(navController = navController)
        }


        // Category Detail Page: Displays movies or favorites for a specific category.
        // Accepts a key (e.g., "Drama", "yourFavorites") as a parameter.
        composable("categoryDetailPage/{key}",
            arguments = listOf(navArgument("key"){type = NavType.StringType})){
            val json = it.arguments?.getString("key")
            val keyJson = Gson().fromJson(json,String::class.java)

            CategoryDetailPage(keyJson, categoryDetailPageViewModel = categoryDetailPageViewModel,navController = navController)
        }

        // Movie Detail Page: Displays details about a specific movie.
        // Accepts a serialized movie object as a parameter.
        composable(
            "movieDetailPage/{movie}",
            arguments = listOf(navArgument("movie"){type = NavType.StringType}
            )
        ){
          val json = it.arguments?.getString("movie")
          val movieObject = Gson().fromJson(json,Movies::class.java)
            MovieDetailPage(movieObject, movieDetailPageViewModel = movieDetailPageViewModel)
        }

    }
}