package com.example.mvvmapp

class BookRepository {


    private val books = mutableListOf<Book>(
        Book("mm", "nn", "asdsad"),
        Book("mm", "nn", "asdsad"),
        Book("mm", "nn", "asdsad"),
        Book("mm", "nn", "asdsad"),
        Book("mm", "nn", "asdsad"),
    )

    fun getBooks() = books


    fun addBook(book: Book) {
        books.add(book)

    }
}