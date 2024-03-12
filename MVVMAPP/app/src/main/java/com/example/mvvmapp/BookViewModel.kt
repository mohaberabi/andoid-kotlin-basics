package com.example.mvvmapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {


    val booksLiveData = MutableLiveData<List<Book>>()

    init {
        getAllBooks()
    }

    fun getAllBooks() {
        val books = bookRepository.getBooks()
        booksLiveData.value = books
    }

    fun addContact(book: Book) {
        bookRepository.addBook(book)
    }
}