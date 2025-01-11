package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentBlack
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentYellow
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.theme.yellow
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.SearchPageViewModel

// Composable function for the Search Page.
// Displays a search bar where users can search for movies by name. The search results are dynamically updated
// as the user types, and a grid layout is used to display the movie results.
// The page also includes a clear button to reset the search and display all movies.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(searchPageViewModel: SearchPageViewModel, navController: NavController) {
    // Observe the movie list and manage search text state.
    val movieList = searchPageViewModel.movieList.observeAsState(initial = listOf())
    val searchText = remember { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }

    // Load all movies when the composable is launched.
    LaunchedEffect(Unit) {
        searchPageViewModel.viewMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(30.dp))
        // Row containing the search bar and a dynamic icon for clearing or initiating search.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
                // Search input field.
                TextField(
                    value = searchText.value,
                    onValueChange = {
                        searchText.value = it
                        isSearching.value = true
                        searchPageViewModel.searchMovie(it) // Updated search results dynamically.
                    },
                    label = { Text(text = stringResource(R.string.search_movie)
                        ,fontFamily = funnelsans,
                        fontWeight = FontWeight.Medium,
                        color = white)},
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = white,
                        containerColor = transparentBlack, // Transparent black (50% opacity)
                        focusedIndicatorColor = transparentYellow, // Border color when focused is yellow
                        unfocusedIndicatorColor = transparentBlack, // Border color when not focused is yellow
                        focusedLabelColor = white, // Label color when focused is white
                        unfocusedLabelColor = white
                    )
                )

            // Search or clear button based on `isSearching` state.
            if(isSearching.value){
                Image(
                    painter = painterResource(R.drawable.exiticon),
                    contentDescription = stringResource(R.string.search_icon),
                    modifier = Modifier
                        .clickable {
                            isSearching.value = !isSearching.value
                            searchText.value = "" // Cleared the search field.
                            searchPageViewModel.viewMovies() // Reset to show all movies.
                        }
                        .padding(top = 10.dp, end = 35.dp)
                )

            }else{
                Image(
                    painter = painterResource(R.drawable.searchbaricon),
                    contentDescription = stringResource(R.string.search_icon),
                    modifier = Modifier.padding(top = 10.dp, end = 35.dp)

                )
            }




        }

        Spacer(modifier = Modifier.size(50.dp))

        // Display movie results in a grid layout.
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            items(movieList.value) { movie ->
                MovieChip(movie, navController) // Navigated to movie detail on click.
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
    }

}