package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CategoryDetailPageViewModel
import com.skydoves.landscapist.glide.GlideImage

// Composable function for the Category Detail Page.
// Displays a grid of movies or favourites based on the provided category key.
// Handles dynamic content by fetching and filtering data from the ViewModel.
@Composable
fun CategoryDetailPage(
    key: String,
    categoryDetailPageViewModel: CategoryDetailPageViewModel,
    navController: NavController
) {
    // Observing data from the ViewModel.
    val movieList = categoryDetailPageViewModel.movieList.observeAsState(listOf())
    val favouriteList = categoryDetailPageViewModel.favouriteList.observeAsState(listOf())
    // Title for the page, dynamically updated based on the category key.
    val titleText = remember { mutableStateOf("") }
    val context = LocalContext.current

    // Fetched and filtered data when the composable is launched or when the key changes.
    LaunchedEffect(key) {
        if (key == "yourFavorites") {
            categoryDetailPageViewModel.loadFavourites()
            titleText.value = context.getString(R.string.favourite_movies)
        } else if (key == "allMovies") {
            categoryDetailPageViewModel.viewMovies()
            titleText.value = context.getString(R.string.all_movies)
        } else {
            categoryDetailPageViewModel.filterMovies(key)
            if(key == "Drama"){
                titleText.value = context.getString(R.string.drama)
            }else if(key == "Science Fiction"){
                titleText.value = context.getString(R.string.sci_fi)
            }else if(key == "Fantastic"){
                titleText.value = context.getString(R.string.fantastic)
            }else if(key == "Action"){
                titleText.value = context.getString(R.string.action)
            }else{
                titleText.value = context.getString(R.string.unknown_category)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Page title.
        Text(
            text = titleText.value,
            fontFamily = funnelsans,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = white
        )

        // Grid displaying movies or favourites.
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(8.dp)
                .background(black)
        ) {
            when (key) {
                "allMovies" -> {
                items(movieList.value) { movie ->
                    MovieChip(movie, navController)
                }
            }
                "yourFavorites" -> {
                    items(favouriteList.value) { favourite ->
                        MovieChip(favourite, navController)
                    }

                }
                else -> {
                    items(movieList.value) { movie ->
                        MovieChip(movie, navController)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(30.dp))




    }
}
