package com.irempamukcu.bootcampfinalproject.ui.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempamukcu.bootcampfinalproject.data.entity.CartItems
import com.irempamukcu.bootcampfinalproject.data.entity.Favourites
import com.irempamukcu.bootcampfinalproject.data.repo.FavouritesRepository
import com.irempamukcu.bootcampfinalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the Movie Detail Page.
// Manages the data and operations related to movies and favourites.
// Interacts with the MoviesRepository and FavouritesRepository to fetch and filter data.
@HiltViewModel
class MovieDetailPageViewModel @Inject constructor(var moviesRepository: MoviesRepository,
                                                   var favouritesRepository: FavouritesRepository): ViewModel() {

    // LiveData to hold the list of favourite items.
    var favouriteList = MutableLiveData<List<Favourites>>()


    //Fetches all favourites from the repository,filter movies by id and updates the favouriteList LiveData.
    fun loadFilteredFavourites(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val allFavourites = favouritesRepository.loadFavourites()
            favouriteList.value = allFavourites.filter { it.id == id }
        }
    }



    // Saves a movie as a favourite by the FavouritesRepository.
    fun saveFavourites(id:Int,
                       name: String,
                       image: String,
                       price: Int,
                       category: String,
                       rating : Double,
                       year : Int,
                       director : String,
                       description : String){
        CoroutineScope(Dispatchers.Main).launch {
            favouritesRepository.saveFavourites(id,name, image, price, category, rating, year, director, description)
        }
    }

    // Deletes a movie as a favourite by the FavouritesRepository.
    fun deleteFavourites(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                favouritesRepository.deleteFavourites(id)
            } catch (e: Exception) {
                Log.e("MovieDetailPageViewModel", "Error deleting favourite: ${e.message}")
            }
        }
    }

    // Adds or updates a movie in the user's cart. If a matching movie already exists,
    // its order amount is updated; otherwise, a new movie is added to the cart.
    fun addOrUpdateMovie(name: String,
                            image: String,
                            price: Int,
                            category: String,
                            rating : Double,
                            year : Int,
                            director : String,
                            description : String,
                            orderAmount : Int,
                            userName : String,
                            currentName : String){
        CoroutineScope(Dispatchers.Main).launch {
            val cartItems = moviesRepository.viewCart(userName)
            val matchingItem = cartItems.find { it.name == currentName }

            if(matchingItem!= null){
                moviesRepository.deleteMovieFromCart(matchingItem.cartId, userName)

                val newOrderAmount = matchingItem.orderAmount + orderAmount

                moviesRepository.addMoviesToCart(
                    matchingItem.name,
                    matchingItem.image,
                    matchingItem.price,
                    matchingItem.category,
                    matchingItem.rating,
                    matchingItem.year,
                    matchingItem.director,
                    matchingItem.description,
                    newOrderAmount,
                    userName
                )
            }else{
                moviesRepository.addMoviesToCart(
                    name, image, price, category, rating, year,
                    director, description, orderAmount, userName)
            }
        }

    }


}