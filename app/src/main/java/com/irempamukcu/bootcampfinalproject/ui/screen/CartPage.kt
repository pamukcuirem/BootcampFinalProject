package com.irempamukcu.bootcampfinalproject.ui.screen

import android.icu.text.ListFormatter.Width
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.data.entity.CartItems
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentBlack
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentYellow
import com.irempamukcu.bootcampfinalproject.ui.theme.trasnparentRed
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.theme.yellow
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CartPageViewModel
import com.skydoves.landscapist.glide.GlideImage

// This composable serves as the start destination for CartScreenNavigation.
// It functions as the Cart Screen, displaying items added to the cart and providing options to manage them.
@Composable
fun CartPage(cartPageViewModel: CartPageViewModel, navController: NavController){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val cartItems = cartPageViewModel.cartItems.observeAsState(emptyList())

    val totalAmount = cartItems.value.sumOf { it.price * it.orderAmount }


    // Fetching cart items when the composable is first displayed.
    LaunchedEffect (true){
        cartPageViewModel.viewCart("irempamukcu")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LazyColumn positioned to take up the available space
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                // Adjust for BottomBar height
        ) {
            items(cartItems.value) { cartItem ->
                Column {
                    CartChip(cartItem, width = screenWidth, height = screenHeight / 5, cartPageViewModel)
                }
            }


        }
        Column(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(

                        Color(0xED192434), // Semi-transparent black at the bottom
                        Color(0xFF151C26) // Fully transparent at the top
                    )
                )
            )

        ){
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top){

                Column (verticalArrangement = Arrangement.SpaceEvenly){
                    Text(text= "Toplam Tutar",
                        fontFamily = funnelsans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = white)
                    Spacer(modifier = Modifier. size(15.dp))

                    Text(
                        text= "$totalAmount ₺",
                        fontFamily = funnelsans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = white)
                }
                // Showing a disabled button if the cart is empty; otherwise, allow completing the purchase.
                if(totalAmount == 0){
                    AnimatedBasketButton(screenHeight = screenHeight,false) { }
                }else{
                    AnimatedBasketButton(screenHeight = screenHeight,true) {
                        cartPageViewModel.deleteAllMoviesFromCart("irempamukcu")
                        navController.navigate("purchaseCompletedPage")
                    }
                }


            }
                }

            }
        }
