package com.irempamukcu.bootcampfinalproject.data.repo

import com.irempamukcu.bootcampfinalproject.data.datasource.FavouritesDataSource
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites

// This repository class provides an abstraction layer between the data source and the rest of the application.
// It manages operations related to the "Favourites" entity.
class FavouritesRepository(var favouritesDataSource: FavouritesDataSource) {

    // Loads the list of favourite items by data source.
    suspend fun loadFavourites(): List<Favourites> = favouritesDataSource.loadFavourites()


    // Saves a favourite item by data source.
    suspend fun saveFavourites(id: Int,
                               name: String,
                               image: String,
                               price: Int,
                               category: String,
                               rating : Double,
                               year : Int,
                               director : String,
                               description : String) =
        favouritesDataSource.saveFavourites(id, name, image, price, category, rating,
            year, director, description)


    // Deletes a favourite item by data source.
    suspend fun deleteFavourites(id: Int) {
        favouritesDataSource.deleteFavourites(id)
    }
}