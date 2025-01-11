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


// ViewModel class for the Main Page.
// It manages the cart-related data and business logic, interacting with the MoviesRepository to handle movie operations.
@HiltViewModel
class MainPageViewModel @Inject constructor(var moviesRepository: MoviesRepository): ViewModel(){
    var movieList = MutableLiveData<List<Movies>>()


    // Fetches the movies and updates the LiveData.
    fun viewMovies(){
        CoroutineScope(Dispatchers.Main).launch{
            movieList.value = moviesRepository.viewMovies()
        }
    }

    // Fetches the movies, filter them by id and updates the LiveData.
    fun getMoviesByIds(ids: List<Int>): List<Movies> {
        return ids.mapNotNull { id ->
            movieList.value?.find { it.id == id }
        }
    }




}