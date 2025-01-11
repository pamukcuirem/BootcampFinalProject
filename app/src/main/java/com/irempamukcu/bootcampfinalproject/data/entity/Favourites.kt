package com.irempamukcu.bootcampfinalproject.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

// This data class represents a "Favourites" entity in the local Room database.
// Each instance of this class corresponds to a row in the "favouritetable".
//You can reach table from 'Assets'.
@Entity(tableName = "favouritetable")
data class Favourites(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") @NotNull var id: Int,                // Unique identifier for the favourite item. AutoGenerate is false because id should be the same with movie item.
    @ColumnInfo(name= "name") @NotNull var name: String,          // Name of the favourite item.
    @ColumnInfo(name= "image") @NotNull var image: String,        // Path to the image of the favourite item.
    @ColumnInfo(name= "price") @NotNull var price: Int,           // Price of the favourite item.
    @ColumnInfo(name= "category") @NotNull var category: String,  // Category of the favourite item.
    @ColumnInfo(name= "rating") @NotNull var rating: Double,      // Rating of the favourite item.
    @ColumnInfo(name= "year") @NotNull var year: Int,             //Release year of the favourite item.
    @ColumnInfo(name= "director") @NotNull var director: String,  // Director of the favourite item.
    @ColumnInfo(name= "description") @NotNull var description: String, // Description of the favourite item.
)
