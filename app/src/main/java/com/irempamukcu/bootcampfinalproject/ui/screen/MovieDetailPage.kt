package com.irempamukcu.bootcampfinalproject.ui.screen

import android.content.Context
import android.graphics.Movie
import android.widget.Space
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.irempamukcu.bootcampfinalproject.R
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.ui.theme.black
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsans
import com.irempamukcu.bootcampfinalproject.ui.theme.funnelsansitalic
import com.irempamukcu.bootcampfinalproject.ui.theme.orange
import com.irempamukcu.bootcampfinalproject.ui.theme.red
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentBlack
import com.irempamukcu.bootcampfinalproject.ui.theme.transparentYellow
import com.irempamukcu.bootcampfinalproject.ui.theme.trasnparentRed
import com.irempamukcu.bootcampfinalproject.ui.theme.white
import com.irempamukcu.bootcampfinalproject.ui.theme.yellow
import com.irempamukcu.bootcampfinalproject.ui.viewmodel.MovieDetailPageViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter
import kotlin.math.ceil


//Composable function for the Movie Detail Page.
// Displays detailed information about a selected movie, including its image, rating, description, director, category, and year.
// Users can also add the movie to their cart, mark it as a favorite, and manage the purchase quantity.
@Composable
fun MovieDetailPage(movie: Movies, movieDetailPageViewModel: MovieDetailPageViewModel) {
    val amount = remember { mutableStateOf(1) } // Tracks the number of items to purchase.
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val favouriteFilteredList by movieDetailPageViewModel.favouriteList.observeAsState(emptyList())

    // Observing the favorite list to determine if the current movie is marked as favorite.
    val isFavorite = favouriteFilteredList.any { it.id == movie.id }
    val iconStatus = remember { mutableStateOf(false) }


    // Load the filtered favorites list when the composable is launched.
    LaunchedEffect(Unit) {
        movieDetailPageViewModel.loadFilteredFavourites(movie.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = black)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val moviePictureUrl = "http://kasimadalan.pe.hu/movies/images/${movie.image}"

        // Movie rating displayed as stars.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RatingChip(movie)
        }

        Text((movie.rating / 2.0).toString(), color = white, fontFamily = funnelsans)

        Spacer(modifier = Modifier.size(20.dp))

        // Movie poster with a favorite icon.
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 450.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            GlideImage(
                imageModel = moviePictureUrl,
                modifier = Modifier
                    .size(width = 300.dp, height = 450.dp)
                    .clip(RoundedCornerShape(8.dp))
            )


            Image(
                painter = painterResource(
                    if (isFavorite || iconStatus.value) R.drawable.favoriteiconclick else R.drawable.favoriteicon
                ),
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(50.dp)
                    .clickable {
                        if (isFavorite || iconStatus.value) {
                            movieDetailPageViewModel.deleteFavourites(movie.id)
                            iconStatus.value = !iconStatus.value
                        } else {
                            movieDetailPageViewModel.saveFavourites(
                                movie.id,
                                movie.name,
                                movie.image,
                                movie.price,
                                movie.category,
                                movie.rating,
                                movie.year,
                                movie.director,
                                movie.description
                            )
                            iconStatus.value = !iconStatus.value
                        }
                        // Reload favorites to update the UI.
                        movieDetailPageViewModel.loadFilteredFavourites(movie.id)

                    }
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        // Movie title.
        Text(
            text = movie.name,
            fontFamily = funnelsans,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = white
        )

        Spacer(modifier = Modifier.size(10.dp))

        // Display movie metadata (director, year, category).
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = movie.director,
                fontFamily = funnelsansitalic,
                fontSize = 18.sp,
                color = white
            )

            Text(
                text = stringResource(R.string.column),
                fontFamily = funnelsansitalic,
                fontSize = 18.sp,
                color = white
            )

            Text(
                text = movie.year.toString(),
                fontFamily = funnelsansitalic,
                fontSize = 18.sp,
                color = white
            )

            Text(
                text = context.getString(R.string.column),
                fontFamily = funnelsansitalic,
                fontSize = 18.sp,
                color = white
            )

            Text(
                text = when (movie.category) {
                    "Drama" -> stringResource(R.string.drama)
                    "Action" -> stringResource(R.string.action)
                    "Science Fiction" -> stringResource(R.string.sci_fi)
                    "Fantastic" -> stringResource(R.string.fantastic)
                    else -> stringResource(R.string.unknown_category)
                },

                fontFamily = funnelsansitalic,
                fontSize = 18.sp,
                color = white
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        // Quantity and price management.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column (modifier = Modifier

                .fillMaxHeight()){

                Text(modifier = Modifier.padding(bottom = 15.dp, start = 10.dp),
                    text = stringResource(R.string.amount),
                    fontFamily = funnelsans,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = white
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.minusicon),
                        contentDescription = stringResource(R.string.minus_icon),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { if (amount.value > 1) amount.value-- },
                        tint = white
                    )

                    Text(
                        text = amount.value.toString(),
                        fontFamily = funnelsans,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )

                    Icon(
                        painter = painterResource(R.drawable.plusicon),
                        contentDescription = stringResource(R.string.plus_icon),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { amount.value++ },
                        tint = white
                    )

                }


            }


            Column (modifier = Modifier
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    modifier = Modifier.padding(bottom = 15.dp, end = 10.dp),
                    text = stringResource(R.string.price),
                    fontFamily = funnelsans,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = white
                )


                // Right group: Price
                Text(
                    text = "${movie.price} ₺ ",
                    fontFamily = funnelsans,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = white
                )

            }



        }


        Spacer(modifier = Modifier.size(40.dp))

        // Add to cart button with animation.
        AnimatedButton(movie, amount = amount, movieDetailPageViewModel, context = context)

        Spacer(modifier = Modifier.size(30.dp))

        // Expandable movie description box.
        ExpandableBoxChip(movie = movie)

        Spacer(modifier = Modifier.size(80.dp))
    }
}
