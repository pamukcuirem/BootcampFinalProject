package com.irempamukcu.bootcampfinalproject.data.entity

// This data class represents an item in the user's cart.
data class CartItems(
    var cartId : Int,         // Unique identifier for the cart item.
    var name : String,        // Name of the movie in the cart.
    var image : String,       // Path to the image of the movie.
    var price : Int,          // Price of the movie.
    var category : String,    // Category to which the movie belongs.
    var rating : Double,      // Rating of the movie
    var year : Int,           // Release year of the movie.
    var director : String,    // Director of the movie.
    var description : String, // Description of the movie.
    var orderAmount : Int,    // Quantity of the movie added to the cart.
    var userName : String     //Username of the user who added the item to the cart (to manage different carts)
)

