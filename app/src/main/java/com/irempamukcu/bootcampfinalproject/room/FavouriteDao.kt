package com.irempamukcu.bootcampfinalproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites

// Dao interface for the "Favourites" entity.
// Defines database operations that can be performed on the "favouritetable". For reaching to table you can check "assets"
@Dao
interface FavouriteDao {

    // Fetches all the favourite items from the database. Returns a list of all favourites stored in the "favouritetable".
    @Query("SELECT * FROM favouritetable")
    suspend fun loadFavourites(): List<Favourites>

    // Inserts a new favourite item into the database.
    @Insert
    suspend fun saveFavourites(favourites: Favourites)

    // Deletes a new favourite item into the database.
    @Delete
    suspend fun deleteFavourites(favourites: Favourites)
}