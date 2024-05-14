package com.example.shelfy.screens.browse

import androidx.lifecycle.ViewModel
import com.example.shelfy.api.GoogleBooksAPIProvider
import com.example.shelfy.model.Item
import com.example.shelfy.model.cb.GoogleBooksResult


class BrowseViewModel : ViewModel() {
    private val googleBooksAPIProvider = GoogleBooksAPIProvider.GoogleBooksAPIProvider()

    fun searchBooks(query: String) {
        // Assuming you have access to the API key
        val apiKey = "AIzaSyD0jak8NAVY_dGLIs3EAqiNysNkoQBhcVs"
        val maxResults = 10 // Adjust the number of results as needed

        googleBooksAPIProvider.searchBooks(query, apiKey, object : GoogleBooksResult {
            override fun onDataFetchedSuccess(books: List<Item>) {
                // Handle successful data fetching
            }

            override fun onDataFetchedFailed() {
                // Handle data fetching failure
            }
        })
    }
}
