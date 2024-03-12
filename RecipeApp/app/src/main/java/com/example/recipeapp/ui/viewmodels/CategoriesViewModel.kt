package com.example.recipeapp.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.core.ApiConst
import com.example.recipeapp.data.model.Category
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {


    private val _categoriesState = mutableStateOf(CategoryState())


    val categoriesState: State<CategoryState> = _categoriesState


    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                val response = ApiConst.recipeService.getCategories()
                _categoriesState.value =
                    _categoriesState.value.copy(
                        loading = false,
                        categories = response.categories,
                        error = ""
                    )
            } catch (e: Exception) {
                _categoriesState.value =
                    _categoriesState.value.copy(loading = false, error = e.toString())
            }
        }
    }

    data class CategoryState(
        val loading: Boolean = true,
        val categories: List<Category> = emptyList(),
        val error: String = ""
    )
}