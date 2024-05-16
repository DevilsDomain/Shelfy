package com.example.shelfy.screens.browse

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shelfy.api.GoogleBooksAPIProvider
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.Shelf
import com.example.shelfy.db.ShelfRepository
import com.example.shelfy.model.Item
import com.example.shelfy.model.cb.GoogleBooksResult


class BrowseViewModel(application: Application) : AndroidViewModel(application) {
    private val googleBooksAPIProvider = GoogleBooksAPIProvider.GoogleBooksAPIProvider()
    // LiveData to hold the fetched books
    private val _books = MutableLiveData<List<Item>>()
    val books: LiveData<List<Item>> = _books

    val database = AppDatabase.getDatabase(application)
    val repository = ShelfRepository(database.shelfDao())

    fun addBookToShelf(book: Item) {
        val shelf = Shelf(
            id = book.id, // Use the book ID as the primary key
            title = book.volumeInfo?.title ?: "",
            author = book.volumeInfo?.authors?.joinToString(", ") ?: "",
            description = book.volumeInfo?.description ?: "",
            imageUrl = book.volumeInfo?.imageLinks?.thumbnail ?: "",
            status = "New",
            finishedAt = null
        )
        repository.insertBook(shelf)
    }

    fun searchBooks(query: String) {
        // Assuming you have access to the API key
        val apiKey = "AIzaSyD0jak8NAVY_dGLIs3EAqiNysNkoQBhcVs"

        googleBooksAPIProvider.searchBooks(query, apiKey, object : GoogleBooksResult {
            override fun onDataFetchedSuccess(books: List<Item>) {
                _books.value = books

            }

            override fun onDataFetchedFailed() {
                // Handle data fetching failure
            }
        })
    }
}
