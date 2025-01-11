package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irempamukcu.bootcampfinalproject.local.AppPref
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.skydoves.landscapist.glide.GlideImage

// Composable function for the Splash Page.
// Displays an animated GIF along with a title as a splash screen.
// The function increments and saves the launch counter using `AppPref`.
@Composable
fun SplashPage(){
    val context = LocalContext.current
    val appPref = AppPref(context)
    val resultCounter = remember { mutableStateOf(0) }

    // Increment the launch counter when the composable is first launched.
    LaunchedEffect(Unit) { // Use Unit instead of true
        val counter = appPref.getCounter()
        resultCounter.value = counter + 1
        appPref.setCounter(counter + 1)
    }


    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    Column(modifier = Modifier
        .fillMaxSize()
        .background(white),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally){

        // Box to center the content (GIF and text) on the screen.
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center){

            // Displayed the animated GIF.
            GlideImage(
                imageModel = R.drawable.popgif, // Replace with your drawable GIF name
                contentDescription = stringResource(R.string.splash_gif),
                modifier = Modifier
                    .size(width = screenWidth.dp, height = screenHeight.dp)
            )

            // Displayed the app title below the GIF.
            Text(
                text = stringResource(R.string.watch_out),
                fontFamily = funnelsans,
                fontSize = 65.sp,
                fontWeight = FontWeight.ExtraBold,
                color = white,
                modifier = Modifier.padding(top = 350.dp)
            )

        }



    }
}