package com.irempamukcu.bootcampfinalproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites

// This abstract class represents the Room database for the application.
// It defines the database configuration and serves as the main access point for the underlying connection to the app's SQLite database.
@Database(entities = [Favourites::class], version = 5)
abstract class Database : RoomDatabase(){

    // Provides access to the Dao for performing database operations on the Favourites entity.
    // Returns an instance of FavouriteDao for interacting with the database.
    abstract fun getFavouriteDao() : FavouriteDao
}