package com.irempamukcu.bootcampfinalproject.data.entity

// This data class represents the response structure for a user's cart.
data class CartResponse(
    var movie_cart : List<CartItems> // A list of CartItems representing the movies in the user's cart.
)
