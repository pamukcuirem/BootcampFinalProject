package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentOrange
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentYellow
import com.irempamukcu.bootcampfinalproject.ui.theme.trasnparentRed
import com.irempamukcu.bootcampfinalproject.ui.theme.white

// Composable function for the Category Page.
// Displays a grid-like arrangement of category buttons, allowing users to navigate to different movie categories or their favorite movies.
@Composable
fun CategoryPage(navController: NavController){

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val context = LocalContext.current

    Column (modifier = Modifier
        .fillMaxSize()
        .background(black),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){



        // Row for "All Movies" and "Favorite Movies" categories.
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){

            SubjectsChip(context.getString(R.string.all_movies), width = (screenWidth /2.5).toInt(), height = screenHeight/14,
                navController = navController, key = "allMovies"
                )

            SubjectsChip(context.getString(R.string.favourite_movies), width = (screenWidth /2.5).toInt(), height = screenHeight/14,
                navController = navController, key = "yourFavorites"
                )
        }

        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
            ){
            SubjectsChip(context.getString(R.string.drama), width = (screenWidth /2.5).toInt(), height = screenHeight/14,
                navController = navController, key = "Drama"
                )

            SubjectsChip(context.getString(R.string.sci_fi), width = (screenWidth /2.5).toInt(), height = screenHeight/14,
                navController = navController, key = "Science Fiction"
                )
        }

        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            SubjectsChip(context.getString(R.string.action),width = (screenWidth /2.5).toInt(), height = screenHeight/14,
                navController = navController, key = "Action"
                )

            SubjectsChip(context.getString(R.string.fantastic), width = (screenWidth /2.5).toInt(), height = screenHeight/14,
                navController = navController, key = "Fantastic"
                )
        }




        Spacer(modifier = Modifier.size(180.dp))
    }
}




