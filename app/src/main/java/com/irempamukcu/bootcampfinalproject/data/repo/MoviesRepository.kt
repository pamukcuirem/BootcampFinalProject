package com.irempamukcu.bootcampfinalproject.data.repo

import com.irempamukcu.bootcampfinalproject.data.datasource.MoviesDataSource
import com.irempamukcu.bootcampfinalproject.data.entity.CartItems
import com.irempamukcu.bootcampfinalproject.data.entity.Movies

// This repository class provides an abstraction layer between the data source and the rest of the application.
// It handles operations related to movies and cart items.
class MoviesRepository(var moviesDataSource: MoviesDataSource) {

    // Fetches the list of movies by data source.
    suspend fun viewMovies() : List<Movies> = moviesDataSource.viewMovies()

    // Fetches the list of cart items for a specific user by data source.
    suspend fun viewCart(userName: String) : List<CartItems> = moviesDataSource.viewCart(userName)

    // Adds a movie to the user's cart by data source
    suspend fun addMoviesToCart(name: String,
                                image: String,
                                price: Int,
                                category: String,
                                rating : Double,
                                year : Int,
                                director : String,
                                description : String,
                                orderAmount : Int,
                                userName : String) =
        moviesDataSource.addMovieToCart(name, image, price, category, rating,
        year, director, description, orderAmount, userName)

    // Deletes a movie from the user's cart by data source.
    suspend fun deleteMovieFromCart(cartId : Int, userName: String) = moviesDataSource.deleteMovieFromCart(cartId, userName)



}