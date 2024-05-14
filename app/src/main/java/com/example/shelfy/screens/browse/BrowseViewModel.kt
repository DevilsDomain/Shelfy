package com.example.shelfy.screens.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shelfy.api.GoogleBooksAPIProvider
import com.example.shelfy.model.Item
import com.example.shelfy.model.cb.GoogleBooksResult


class BrowseViewModel : ViewModel() {
    private val googleBooksAPIProvider = GoogleBooksAPIProvider.GoogleBooksAPIProvider()
    // LiveData to hold the fetched books
    private val _books = MutableLiveData<List<Item>>()
    val books: LiveData<List<Item>> = _books

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
