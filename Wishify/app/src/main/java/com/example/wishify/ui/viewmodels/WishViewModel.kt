package com.example.wishify.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishify.app.Graph
import com.example.wishify.data.model.WishItem
import com.example.wishify.data.repository.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository:
    WishRepository = Graph.wishRepository
) :
    ViewModel() {

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.listenToWishes()
        }
    }


    fun addWish(wish: WishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }

    fun deleteWish(wish: WishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }

    fun updateWish(wish: WishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }


    fun getWishById(id: String) = wishRepository.getWishById(id)


    private val _wishState = mutableStateOf(WishState("", ""))


    private lateinit var getAllWishes: Flow<List<WishItem>>

    val wishState = _wishState

    fun titleChanged(v: String) {
        _wishState.value = wishState.value.copy(title = v)
    }

    fun describtionChanged(v: String) {
        _wishState.value = wishState.value.copy(describtion = v)
    }

    data class WishState(val title: String, val describtion: String) {
        val canAdd: Boolean = title.isNotBlank() && describtion.isNotBlank()
    }
}