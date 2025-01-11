package com.irempamukcu.bootcampfinalproject.retrofit

import com.irempamukcu.bootcampfinalproject.data.entity.CartResponse
import com.irempamukcu.bootcampfinalproject.data.entity.MoviesResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

// Interface defining API endpoints and methods for performing network operations related to movies and the cart.
interface MoviesDao {

    // Fetches all movies from the server. Returns a MoviesResponse object containing a list of movies.
    @GET("movies/getAllMovies.php")
    suspend fun viewMovies() : MoviesResponse

    // Fetches the cart items for a specific user. Returns a CartResponse object containing the cart items.
    @POST("movies/getMovieCart.php")
    @FormUrlEncoded
    suspend fun viewCart(@Field("userName") userName: String) : CartResponse

    // Deletes a movie from the user's cart. Returns a CartResponse object representing the updated cart.
    @POST("movies/deleteMovie.php")
    @FormUrlEncoded
    suspend fun deleteMovieFromCart(@Field("cartId") cartId:Int,
                                    @Field("userName") userName: String
                                    ) : CartResponse


    // Adds a movie to the user's cart. Returns a CartResponse object representing the updated cart.
    @POST("movies/insertMovie.php")
    @FormUrlEncoded
    suspend fun addMovieToCart(@Field("name")name: String,
                               @Field("image")image: String,
                               @Field("price")price: Int,
                               @Field("category")category: String,
                               @Field("rating")rating : Double,
                               @Field("year")year : Int,
                               @Field("director")director : String,
                               @Field("description")description : String,
                               @Field("orderAmount")orderAmount : Int,
                               @Field("userName")userName : String) : CartResponse


}