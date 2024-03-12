package com.example.mvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var bookRepository: BookRepository
    private lateinit var bookViewModel: BookViewModel
    private lateinit var viewModelFactory: BookViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        bookRepository = BookRepository()
        viewModelFactory = BookViewModelFactory(bookRepository)
        bookViewModel = ViewModelProvider(this, viewModelFactory).get(BookViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}