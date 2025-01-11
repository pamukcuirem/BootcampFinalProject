package com.irempamukcu.bootcampfinalproject.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This class is responsible for creating and providing a Retrofit client instance.
// It ensures that the Retrofit client is properly configured with the base URL and Gson converter.
class RetrofitClient {
    companion object{

        // Creates and returns a Retrofit client instance.
        //Returns a Retrofit instance configured with the provided base URL and Gson converter.
        fun getClient(baseUrl : String): Retrofit{
            return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}