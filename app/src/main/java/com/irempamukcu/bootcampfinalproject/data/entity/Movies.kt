package com.irempamukcu.bootcampfinalproject.data.entity

// This data class represents a movie and its associated details.
data class Movies(
    var id : Int,               // Unique identifier for the movie.
    var name : String,          // Name of the movie.
    var image : String,         // Path to the image of the movie.
    var price : Int,            // Price of the movie.
    var category : String,      // Category to which the movie belongs
    var rating : Double,        // Rating of the movie.
    var year : Int,             // Release year of the movie.
    var director : String,      // Director of the movie.
    var description : String    // Description of the movie.
)
