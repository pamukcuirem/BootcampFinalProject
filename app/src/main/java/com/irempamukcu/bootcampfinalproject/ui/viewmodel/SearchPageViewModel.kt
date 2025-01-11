package com.irempamukcu.bootcampfinalproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


// ViewModel for the Search Page.
// Manages the movie list data and handles operations for fetching and searching movies.
// Interacts with the MoviesRepository to retrieve movie data.
@HiltViewModel
class SearchPageViewModel @Inject constructor(var moviesRepository: MoviesRepository): ViewModel()  {
    // LiveData object that holds the list of movies to display on the Search Page.
    // It used for showing updated data while searching
    var movieList = MutableLiveData<List<Movies>>()

    // Fetches the complete list of movies from the repository and updates the movieList LiveData.
    fun viewMovies(){
        CoroutineScope(Dispatchers.Main).launch{
            movieList.value = moviesRepository.viewMovies()
        }
    }



    // Searches for movies by matching the search word against movie 'names' and 'directors'.
    // Updates the movieList LiveData with the filtered results.
    fun searchMovie(searchWord: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val allMovies = moviesRepository.viewMovies() ?: listOf()
            movieList.value = if (searchWord.isBlank()) {
                allMovies
            } else {
                allMovies.filter { movie ->
                    movie.name.contains(searchWord, ignoreCase = true) ||
                            movie.director.contains(searchWord, ignoreCase = true)
                }
            }
        }
    }



}