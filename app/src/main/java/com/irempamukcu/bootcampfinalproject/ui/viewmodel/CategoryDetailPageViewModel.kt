package com.irempamukcu.bootcampfinalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.data.repo.FavouritesRepository
import com.irempamukcu.bootcampfinalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the Category Detail Page.
// Manages the data and operations related to movies and favourites.
// Interacts with the MoviesRepository and FavouritesRepository to fetch and filter data.
@HiltViewModel
class CategoryDetailPageViewModel @Inject constructor(var moviesRepository: MoviesRepository,
    var favouritesRepository: FavouritesRepository
 ): ViewModel() {
    // LiveData to hold the list of movies.
    var movieList = MutableLiveData<List<Movies>>()
    // LiveData to hold the list of favourite items.
    var favouriteList = MutableLiveData<List<Favourites>>()

    //Fetches all movies from the repository and updates the movieList LiveData.
    fun viewMovies(){
        CoroutineScope(Dispatchers.Main).launch{
            movieList.value = moviesRepository.viewMovies()
        }
    }


    // Loads all favourite items from the repository and updates the favouriteList LiveData.
    fun loadFavourites(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                favouriteList.value = favouritesRepository.loadFavourites()
            } catch (e: Exception) {
                Log.e("CategoryDetailPage", "Error loading favourites: ${e.message}")
            }
        }
    }




    // Filters the list of movies by category and updates the movieList LiveData.
    fun filterMovies(key: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val allMovies = moviesRepository.viewMovies()
            val filteredMovies = allMovies.filter { it.category == key }
            movieList.value = filteredMovies
        }
    }


}