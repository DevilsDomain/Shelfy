package com.example.shelfy.model.cb

import com.example.shelfy.model.Item

interface GoogleBooksResult {
    fun onDataFetchedSuccess(books: List<Item>)
    fun onDataFetchedFailed()
}