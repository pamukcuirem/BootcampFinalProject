package com.irempamukcu.bootcampfinalproject.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempamukcu.bootcampfinalproject.data.entity.CartItems
import com.irempamukcu.bootcampfinalproject.data.entity.Movies
import com.irempamukcu.bootcampfinalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel class for the Cart Page.
// It manages the cart-related data and business logic, interacting with the MoviesRepository to handle cart operations.
@HiltViewModel
class CartPageViewModel @Inject constructor(var moviesRepository: MoviesRepository) : ViewModel(){
    // LiveData object that holds the list of cart items.
    var cartItems = MutableLiveData<List<CartItems>>()

   // Fetches the cart items for a specific user and updates the LiveData. Uses userName for determining cart's owner.
    fun viewCart(userName: String){
        CoroutineScope(Dispatchers.Main).launch {
            val fetchedCartItems = moviesRepository.viewCart(userName)
            cartItems.value = fetchedCartItems
        }
    }

    // Deletes the cart item by id and triggers viewCart to update cartItems. Uses userName for determining cart's owner
    fun deleteMovieFromCart(cartId : Int, userName: String){
        CoroutineScope(Dispatchers.Main).launch {
            moviesRepository.deleteMovieFromCart(cartId, userName)
            viewCart(userName)

        }
    }

    // Deletes all cart items. Uses userName for determining cart's owner.
    // It used to clean the basket after completion the order.
    fun deleteAllMoviesFromCart(userName: String){
        CoroutineScope(Dispatchers.Main).launch {
            val cartItemsToDelete = moviesRepository.viewCart(userName)
            for(i in cartItemsToDelete){
                deleteMovieFromCart(i.cartId,userName)
            }
        }

    }



}