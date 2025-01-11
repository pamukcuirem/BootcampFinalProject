package com.irempamukcu.bootcampfinalproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.orange
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentBlack
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CartPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CategoryDetailPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MainPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.SearchPageViewModel


// Composable function for managing the main UI structure and navigation.
// It provides a top app bar with a logo and a bottom navigation bar for switching between the main screen, search screen, and cart screen.
// This function uses `Scaffold` to structure the layout and dynamically switches content based on the selected navigation item.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screens(mainPageViewModel: MainPageViewModel, movieDetailPageViewModel: MovieDetailPageViewModel,
            cartPageViewModel: CartPageViewModel, categoryDetailPageViewModel: CategoryDetailPageViewModel,
            searchPageViewModel : SearchPageViewModel

){
    // State to track the currently selected navigation item.
    val selectedItem = remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    // Main scaffold layout with top bar and bottom navigation bar.
    Scaffold(topBar = {

        // Top App Bar with the app logo.
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = black
            ),
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.watchoutlogo),
                    contentDescription = stringResource(R.string.logo_image),
                    modifier = Modifier
                        .size((screenWidth / 2.5).dp)
                        .padding(horizontal = 8.dp, vertical = 20.dp)
                )
            },

        )
    },

        // Bottom Navigation Bar with three items: Home, Search, and Cart.
        bottomBar = {
            androidx.compose.material3.NavigationBar(
                modifier = Modifier
                    .height(height = (screenHeight / 15).dp)
                    .clip(
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xED192434),
                                Color(0xFF151C26)
                            )
                        )
                    ),
                containerColor = Color.Transparent
            ) {
                // Navigation item: Home
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 },
                    label = { Text(text = stringResource(R.string.home)) },
                    icon = {
                        if (selectedItem.value == 0) {
                            Icon(
                                painter = painterResource(R.drawable.homeiconclick),
                                contentDescription = stringResource(R.string.home),
                                modifier = Modifier.height(height = (screenHeight/40).dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.homeicon),
                                contentDescription =stringResource(R.string.home),
                                modifier = Modifier.height(height = (screenHeight/40).dp)
                            )
                        }

                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = orange,
                        selectedTextColor = orange,
                        unselectedIconColor = white,
                        unselectedTextColor = white,
                        indicatorColor = Color.Transparent
                    )
                )

                // Navigation item: Search
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 },
                    label = { Text(text = stringResource(R.string.search),
                        fontFamily = funnelsans) },
                    icon = {
                        if (selectedItem.value == 1) {
                            Icon(
                                painter = painterResource(R.drawable.searchiconclick),
                                contentDescription =stringResource(R.string.search),
                                modifier = Modifier.height(height = (screenHeight/40).dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.searchicon),
                                contentDescription =stringResource(R.string.home),
                                modifier = Modifier.height(height = (screenHeight/40).dp)
                            )
                        }

                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = orange,
                        selectedTextColor = orange,
                        unselectedIconColor = white,
                        unselectedTextColor = white,
                        indicatorColor = Color.Transparent
                    )
                )
                // Navigation item: Cart
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = { selectedItem.value = 2 },
                    label = { Text(text = stringResource(R.string.cart),
                        fontFamily = funnelsans) },
                    icon = {
                        if (selectedItem.value == 2) {
                            Icon(
                                painter = painterResource(R.drawable.basketiconclick),
                                contentDescription = stringResource(R.string.cart),
                                modifier = Modifier.height(height = (screenHeight/40).dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.basketicon),
                                contentDescription = stringResource(R.string.cart),
                                modifier = Modifier.height(height = (screenHeight/40).dp)
                            )
                        }

                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = orange,
                        selectedTextColor = orange,
                        unselectedIconColor = white,
                        unselectedTextColor = white,
                        indicatorColor = Color.Transparent
                    )
                )


            }

        }) { paddingValues ->
        // Main content area based on the selected navigation item.
        Box(modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxHeight()){
            when(selectedItem.value){
                0 -> MainScreenNavigation(mainPageViewModel = mainPageViewModel,
                    movieDetailPageViewModel = movieDetailPageViewModel,
                    categoryDetailPageViewModel = categoryDetailPageViewModel)
                1-> SearchScreenNavigation(searchPageViewModel = searchPageViewModel,
                    movieDetailPageViewModel = movieDetailPageViewModel)
                2 -> CartScreenNavigation(cartPageViewModel = cartPageViewModel)

            }
        }
    }
}