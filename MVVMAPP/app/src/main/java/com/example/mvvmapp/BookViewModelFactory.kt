package com.example.mvvmapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookViewModelFactory(private val bookRepository: BookRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(bookRepository) as T
    }
}