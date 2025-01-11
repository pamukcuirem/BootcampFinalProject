package com.irempamukcu.bootcampfinalproject.retrofit

// Utility class for API-related operations.
// This class provides the base URL and a method to create an instance of MoviesDao for API communication.
class ApiUtilis {
    companion object{
        // The base URL for the API. This is the endpoint where all requests will be sent.
        val BASE_URL = "http://kasimadalan.pe.hu/"

        // This instance is used to make API calls related to movies. Returns a MoviesDao instance created using Retrofit.
        fun viewMoviesDao(): MoviesDao{
            return RetrofitClient.getClient(BASE_URL).create(MoviesDao::class.java)
        }
    }
}