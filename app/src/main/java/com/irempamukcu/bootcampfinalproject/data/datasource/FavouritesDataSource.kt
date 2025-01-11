package com.irempamukcu.bootcampfinalproject.data.datasource

import com.irempamukcu.bootcampfinalproject.data.entity.Favourites
import com.irempamukcu.bootcampfinalproject.room.FavouriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// This Data Source class is created to manage Room database operations related to the "Favourites" entity

class FavouritesDataSource(var favouriteDao: FavouriteDao) {

    //This function is for loading user's favourites from the local database.
    suspend fun loadFavourites(): List<Favourites> = withContext(Dispatchers.IO){
        return@withContext favouriteDao.loadFavourites()
    }

    //This function is for saving user's favourites locally.
    suspend fun saveFavourites(id: Int,
                               name: String,
                               image: String,
                               price: Int,
                               category: String,
                               rating : Double,
                               year : Int,
                               director : String,
                               description : String){
        val newFavourite = Favourites(id,name, image, price, category, rating, year, director, description)
        favouriteDao.saveFavourites(newFavourite)
    }

    //This function is for deleting user's favourites.
    suspend fun deleteFavourites(id: Int) {
        val deleteFavourite = Favourites(id,"", "", 0, "", 0.0, 0, "", "")
        favouriteDao.deleteFavourites(deleteFavourite)
    }
}