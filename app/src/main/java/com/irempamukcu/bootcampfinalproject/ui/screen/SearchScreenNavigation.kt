package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CartPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.SearchPageViewModel

// Composable function for managing navigation within the Search screen.
// This navigation graph includes the Search Page as the start destination and allows navigation to the Movie Detail Page when a movie is selected.
@Composable
fun SearchScreenNavigation(searchPageViewModel: SearchPageViewModel,
                           movieDetailPageViewModel: MovieDetailPageViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "searchPage"){

        // Composable for the Search Page.
        composable("searchPage"){
            SearchPage(searchPageViewModel = searchPageViewModel, navController = navController)
        }

        // Composable for the Movie Detail Page.
        // Accepts a serialized `Movies` object as a navigation argument.
        composable(
            "movieDetailPage/{movie}",
            arguments = listOf(navArgument("movie"){type = NavType.StringType}
            )
        ){
            val json = it.arguments?.getString("movie")
            val movieObject = Gson().fromJson(json, Movies::class.java)
            MovieDetailPage(movieObject, movieDetailPageViewModel = movieDetailPageViewModel)
        }
    }
}