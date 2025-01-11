package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.irempamukcu.bootcampfinalproject.local.AppPref
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentBlack
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentYellow
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MainPageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Composable function for the Main Page.
// This page acts as the home screen, displaying categorized movie lists and allowing navigation to other pages.
// It also includes a one-time prompt for the user to enter their name.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(mainPageViewModel: MainPageViewModel, navController: NavController){
    val movieList = mainPageViewModel.movieList.observeAsState(listOf())

    val context = LocalContext.current
    val appPref = AppPref(context)
    val resultCounter = remember { mutableStateOf(0) }
    val nameText = remember { mutableStateOf("") }

    // Predefined IDs for categorizing movies into sections.
    val bestId = listOf(5,7,9,12,24,15,6)
    val newId = listOf(12,13,14,22,9,6,10)
    val voiceoverId = listOf(8,5,13,17,19,23,22)
    val watchoutId = listOf(21,20,18,17,14,6,5)
    val exploreId = listOf(11,10,21,24,23,16)

    val bestsList = mainPageViewModel.getMoviesByIds(bestId)
    val newsList = mainPageViewModel.getMoviesByIds(newId)
    val voiceoverList = mainPageViewModel.getMoviesByIds(voiceoverId)
    val watchoutList = mainPageViewModel.getMoviesByIds(watchoutId)
    val exploreList = mainPageViewModel.getMoviesByIds(exploreId)



    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val scrollState = rememberScrollState()


    // Fetch movie data and counter value on the first render.
    LaunchedEffect(true) {
        mainPageViewModel.viewMovies()
        resultCounter.value = appPref.getCounter()
    }

    if(resultCounter.value == 1){
        Column (modifier = Modifier
            .fillMaxSize()
            .background(black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            // Prompt for entering the user's name.
            Text(
                stringResource(R.string.type_your_name),
                fontWeight = FontWeight.Bold,
                fontFamily = funnelsans,
                fontSize = 25.sp,
            )

            // Save the user's name and update the counter.
            TextField(
                value = nameText.value,
                onValueChange = {
                    nameText.value = it
                },
                label = { Text(text = stringResource(R.string.type_your_name)
                    ,fontFamily = funnelsans,
                    fontWeight = FontWeight.Medium,
                    color = white
                )},
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = white,
                    containerColor = transparentBlack, // Transparent black (50% opacity)
                    focusedIndicatorColor = transparentBlack, // Border color when focused is yellow
                    unfocusedIndicatorColor = transparentBlack, // Border color when not focused is yellow
                    focusedLabelColor = white, // Label color when focused is white
                    unfocusedLabelColor = white
                ),
                modifier = Modifier.clip(RoundedCornerShape(12.dp))
            )


            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {
                if(nameText.value.isNotEmpty()){
                    CoroutineScope(Dispatchers.Main).launch {
                        appPref.setName(nameText.value)

                        val newCounter = appPref.getCounter()
                        resultCounter.value = newCounter + 1
                        appPref.setCounter(newCounter + 1)

                    }

                }

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = transparentYellow, // Dynamic container color
                    contentColor = black
                )


            ) {
                Text(
                    stringResource(R.string.save),
                    fontFamily = funnelsans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = black)
            }
        }

    }else{

        // Main UI with categorized movie sections.
        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = black),

            ){
            CategoryChip(screenWidth,navController = navController){
                navController.navigate("categoryPage")
            }

            Spacer(modifier = Modifier.size(10.dp))

            // Display movies in the "Explore" section.
            LazyRow(
                modifier = Modifier.size(width = screenWidth.dp, height = 450.dp)
            ) {
                items(exploreList) { movie ->
                    ExploreMovieChip(movie = movie, navController = navController)
                }
            }
            Spacer(modifier = Modifier.size(20.dp))

            // Categorized movie sections.
            if (movieList.value.isNotEmpty()) {
                MoviesChip(stringResource(R.string.best_movies_title),bestsList, navController)
                Spacer(modifier = Modifier.size(23.dp))
                MoviesChip(stringResource(R.string.new_movies_title), newsList, navController)
                Spacer(modifier = Modifier.size(23.dp))
                MoviesChip(stringResource(R.string.voiceover_movies_title), voiceoverList, navController)
                Spacer(modifier = Modifier.size(23.dp))
                MoviesChip(stringResource(R.string.watchout_special_movies_title), watchoutList, navController)
            }

            Spacer(modifier = Modifier.size(40.dp))

        }
    }

}
