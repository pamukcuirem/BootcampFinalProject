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
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MainPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel

// This composable sets up the navigation for the Cart Screen flow.
// It defines the navigation graph for cart-related pages, including the Cart Page and the Purchase Completed Page.
@Composable
fun CartScreenNavigation(cartPageViewModel: CartPageViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "cartPage"){

        // Route for the Cart Page, which displays cart items and allows management.
        composable("cartPage"){
            CartPage(cartPageViewModel = cartPageViewModel, navController = navController)
        }

        // Route for the Purchase Completed Page, which confirms successful purchases.
        composable("purchaseCompletedPage"){
            PurchaseCompletedPage()
        }
    }
}