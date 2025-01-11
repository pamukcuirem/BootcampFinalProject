package com.irempamukcu.bootcampfinalproject

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.irempamukcu.bootcampfinalproject.ui.screen.Screens
import com.irempamukcu.bootcampfinalproject.ui.screen.SplashPage
import com.irempamukcu.bootcampfinalproject.ui.theme.BootcampFinalProjectTheme
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CartPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CategoryDetailPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MainPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.SearchPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainPageViewModel: MainPageViewModel by viewModels()
    val movieDetailPageViewModel: MovieDetailPageViewModel by viewModels()
    val cartPageViewModel: CartPageViewModel by viewModels()
    val categoryDetailPageViewModel: CategoryDetailPageViewModel by viewModels()
    val searchPageViewModel: SearchPageViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = android.graphics.Color.BLACK

        setContent {
            BootcampFinalProjectTheme {
                var showSplashScreen = remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(5000L) // Wait for 5 seconds
                    showSplashScreen.value = false
                }

                if (showSplashScreen.value) {
                    SplashPage()

                } else {
                    Screens(
                        mainPageViewModel = mainPageViewModel,
                        cartPageViewModel = cartPageViewModel,
                        movieDetailPageViewModel = movieDetailPageViewModel,
                        categoryDetailPageViewModel = categoryDetailPageViewModel,
                        searchPageViewModel = searchPageViewModel
                    )
                }
            }
        }
    }
}