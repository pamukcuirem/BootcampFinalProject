package com.irempamukcu.bootcampfinalproject.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.data.entity.CartItems
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentBlack
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentYellow
import com.irempamukcu.bootcampfinalproject.ui.theme.trasnparentRed
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.theme.yellow
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.CartPageViewModel
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel
import com.skydoves.landscapist.glide.GlideImage


//This chip used at -> Movie Detail Page for add to cart button
@Composable
fun AnimatedBasketButton(
    screenHeight: Int,
    isVisible: Boolean, // Visibility flag
    onClick: () -> Unit
) {
    // Creates an infinite transition for color animation when isVisible is true
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = trasnparentRed,
        targetValue = transparentYellow,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animatedColor"
    )

    // Decide the button color based on isVisible.
    // It used for ensuring button doesn't appear when cart is empty
    val buttonColor = if (isVisible) animatedColor else Color.Transparent
    val textColor = if (isVisible) black else Color.Transparent

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 15.dp)
            .height((screenHeight / 18).dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = stringResource(R.string.complete_cart),
            fontFamily = funnelsans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 23.sp,
            color = textColor
        )
    }
}


//This chip used at -> Cart Page for showing cart items
@Composable
fun CartChip(cartItem : CartItems, width: Int, height : Int, cartPageViewModel: CartPageViewModel) {

    Row(
        modifier = Modifier
            .size(width = width.dp, height = height.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        transparentBlack.copy(alpha = 1f), // Start with semi-transparent black
                        transparentBlack.copy(alpha = 0.2f)  // Fade to a lighter transparency
                    ),
                    start = Offset(0f, 0f), // Start from the left
                    end = Offset(width.toFloat(), 0f) // End at the right
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 1f), // Solid white at the start
                        Color.White.copy(alpha = 0f)  // Fully transparent at the end
                    ),
                    start = Offset(0f, 0f), // Start of the gradient
                    end = Offset(width.toFloat(), 0f) // End of the gradient
                ),
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    )
    {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = (width / 3).dp)
                .padding(vertical = 3.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                imageModel = "http://kasimadalan.pe.hu/movies/images/${cartItem.image}",
                modifier = Modifier
                    .size(width = 100.dp, height = 150.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width((width / 3).dp)
                .padding(vertical = 1.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = cartItem.name,
                fontFamily = funnelsans,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                color = white
            )
            Text(
                text = "${cartItem.price * cartItem.orderAmount} ₺",
                fontFamily = funnelsans,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = white
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = (width / 3).dp)
                .padding(vertical = 3.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(R.drawable.binicon),
                contentDescription = stringResource(R.string.bin_icon),
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        cartPageViewModel.deleteMovieFromCart(cartItem.cartId, "irempamukcu")
                    },
                tint = white
            )
            Spacer(modifier = Modifier.size(30.dp))

            Text(
                text = "${cartItem.orderAmount} Adet",
                fontFamily = funnelsans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = white
            )
        }

    }

}

//This chip used at -> Category Detail Page for showing movie images in a list
@Composable
fun MovieChip(movie: Movies, navController: NavController) {
    val moviePictureUrl = "http://kasimadalan.pe.hu/movies/images/${movie.image}"


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                val movieJson = Gson().toJson(movie)
                navController.navigate("movieDetailPage/$movieJson")
            }
    ) {

        GlideImage(
            imageModel = moviePictureUrl,
            modifier = Modifier
                .size(width = 200.dp, height = 300.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(horizontal = 8.dp))

    }

}


//This chip used at -> Category Detail Page for showing favourite movies' images
@Composable
fun MovieChip(favourite: Favourites, navController: NavController) {
    val moviePictureUrl = "http://kasimadalan.pe.hu/movies/images/${favourite.image}"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                val movieJson = Gson().toJson(favourite)
                navController.navigate("movieDetailPage/$movieJson")
            }
    ) {

        GlideImage(
            imageModel = moviePictureUrl,
            modifier = Modifier
                .size(width = 200.dp, height = 300.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(horizontal = 8.dp))

    }


}

//This chip used at ->Category Page for creating Subject Buttons
@Composable
fun SubjectsChip(text : String,width : Int, height : Int,navController: NavController, key : String){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(transparentYellow)
            .height(height.dp)
            .width(width.dp)
            .padding(8.dp)
            .clickable {
                val keyJson = Gson().toJson(key)
                navController.navigate("categoryDetailPage/$keyJson")
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = funnelsans
        )
    }
}

//This chip used at -> Main Page for showing images to explore
@Composable
fun ExploreMovieChip(movie : Movies, navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 450.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    1.dp,
                    Brush.verticalGradient(
                        colors = listOf(
                            black,
                            transparentBlack,
                            Color(0xAAF2D64B)
                        ),
                        startY = 0f,
                        endY = 450f
                    ), RoundedCornerShape(8.dp)
                )
        ) {

            GlideImage(
                imageModel = "http://kasimadalan.pe.hu/movies/images/${movie.image}",
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(12.dp))
            )


            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(height = 250.dp, width = 300.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                black.copy(alpha = 0f),
                                black.copy(alpha = 0.8f)
                            )
                        )
                    )
            )


            Button(
                onClick = {
                    val movieJson = Gson().toJson(movie)
                    navController.navigate("movieDetailPage/$movieJson")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = black
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                yellow.copy(alpha = 0.4f),
                                yellow.copy(alpha = 1f)
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            {
                Text(
                    stringResource(R.string.explore),
                    fontWeight = FontWeight.Bold,
                    fontFamily = funnelsans,
                    fontSize = 25.sp,
                )
            }

        }
    }
}

//This chip used at -> Main Page for showing movie images in a row
@Composable
fun MoviesChip(
    text: String,
    movies: List<Movies>,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size((screenHeight / 4).dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {


            Text(
                text = text,
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = funnelsans,
                modifier = Modifier.padding(start = 20.dp, end = 10.dp)
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies) { movie ->
                val moviePictureUrl = "http://kasimadalan.pe.hu/movies/images/${movie.image}"

                GlideImage(
                    imageModel = moviePictureUrl,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .size(width = 150.dp, height = 225.dp)
                        .padding(horizontal = 8.dp)
                        .clickable {
                            // Serialized the movie object to JSON
                            val movieJson = Gson().toJson(movie)
                            // Navigated to the movie details page
                            navController.navigate("movieDetailPage/$movieJson")
                        }
                )
            }
        }
    }

    Spacer(modifier = Modifier.size(width = screenWidth.dp, height = 20.dp))
}

//This chip used at -> Main Page for creating a category button to navigate Category Page
@Composable
fun CategoryChip(screenWidth : Int,
                 navController: NavController,
                 changePage: (NavController) -> Unit){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(start = 15.dp)
            .background(
                black,
                shape = RoundedCornerShape(20.dp)
            )
            .border(1.dp, white, shape = RoundedCornerShape(8.dp))
            .clickable {
                changePage(navController)
            }
    ) {
        Text(
            modifier = Modifier.padding( horizontal = 15.dp, vertical = 5.dp ),
            text = stringResource(R.string.categories),
            fontFamily = funnelsans,
            color = white,
            fontSize = (screenWidth/30).sp,
            fontWeight = FontWeight.Bold
        )
    }

}

//This chip used at -> Movie Detail Page for creating a animated button for add to cart function
@Composable
fun AnimatedButton(movie: Movies, amount: MutableState<Int>, movieDetailPageViewModel: MovieDetailPageViewModel,
                   context: Context
) {
    // Created an infinite transition for color animation
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    // Animated between trasnparentRed and transparentYellow colors
    val animatedColor by infiniteTransition.animateColor(
        initialValue = trasnparentRed,
        targetValue = transparentYellow ,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animatedColor"
    )

    Button(
        onClick = {
            movieDetailPageViewModel.addOrUpdateMovie(
                movie.name, movie.image, movie.price, movie.category, movie.rating,
                movie.year, movie.director, movie.description, amount.value, "irempamukcu", movie.name
            )

            Toast.makeText(context, context.getString(R.string.added_to_cart), Toast.LENGTH_LONG).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColor,
            contentColor = black
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            stringResource(R.string.add_to_cart),
            fontFamily = funnelsans,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = black
        )
    }
}


//This chip used at -> Movie Detail Page for showing the rating of the movie in a more visual way
@Composable
fun RatingChip(movie: Movies) {
    val newRating = movie.rating / 2.0 // Converted to 5-star scale
    val integerPart = newRating.toInt() // Number of full stars
    val fractionalPart = newRating - integerPart // Fractional part
    val fractionalStarPlace = if (fractionalPart >= 0.5) 1 else 0 // Checked for half-star

    // Displayed full stars
    repeat(integerPart) {
        Image(
            painter = painterResource(R.drawable.onestar),
            contentDescription = stringResource(R.string.full_star)
        )
    }

    // Displayed half-star if needed
    if (fractionalStarPlace == 1) {
        Image(
            painter = painterResource(R.drawable.halfstar),
            contentDescription = stringResource(R.string.half_star)
        )
    }

    // Calculated and display empty stars
    val emptyStars = 5 - (integerPart + fractionalStarPlace)
    repeat(emptyStars) {
        Image(
            painter = painterResource(R.drawable.emptystar),
            contentDescription = stringResource(R.string.empty_star)
        )
    }
}

//This chip used at -> Movie Detail Page for hiding and expanding description of the movie
@Composable
fun ExpandableBoxChip(movie: Movies) {
    var isExpanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .background(
                color = transparentBlack,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isExpanded.value = !isExpanded.value // Toggle expansion
            }
            .animateContentSize() // Smooth height transition
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(R.string.movie_description_title),
                fontFamily = funnelsans,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.White
            )


            if (isExpanded.value) {
                Text(
                    text = movie.description,
                    fontFamily = funnelsans,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


