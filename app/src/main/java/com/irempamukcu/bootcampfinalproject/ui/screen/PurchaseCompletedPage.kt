package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irempamukcu.bootcampfinalproject.local.AppPref
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.white

// Composable function for the Purchase Completed Page.
// Displays a confirmation message to the user after completing a purchase.
// Includes a mascot image and a personalized greeting with the user's name.
@Composable
fun PurchaseCompletedPage(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    // State to hold the user's name.
    val name = remember { mutableStateOf("") }

    // Access context for shared preferences
    val context = LocalContext.current
    val appPref = AppPref(context)

    // Load the user's name when the composable is launched.
    LaunchedEffect (true){
        name.value = appPref.getName()
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .background(black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            Image(painter = painterResource(R.drawable.poppurchaseimage), contentDescription = "Pop Corn Mascot"
            , modifier = Modifier
                    .size(width = (screenWidth).dp, (screenHeight / 2).dp)
                    .padding(horizontal = 20.dp)

            )


            Text(text = stringResource(R.string.order_completed),
                fontFamily = funnelsans,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = white
            )
            Spacer(Modifier.size(20.dp))

            // Personalized thank-you message.
            Text(text = stringResource(R.string.enjoy_movie, name.value),
                fontFamily = funnelsans,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = white)

        }



    }
}