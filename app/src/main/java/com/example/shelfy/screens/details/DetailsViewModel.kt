package com.example.shelfy.screens.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.Shelf
import com.example.shelfy.db.ShelfRepository


class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedBook = MutableLiveData<Shelf?>()
    val selectedBook: LiveData<Shelf?> = _selectedBook

    private val database = AppDatabase.getDatabase(application)
    private val repository = ShelfRepository(database.shelfDao())

    // Function to update the status of the book
    fun updateBookStatus(bookId: String, newStatus: String) {
        repository.updateBookStatus(bookId, newStatus)
    }

    // Function to fetch the selected book from the database and update the LiveData
//    fun getBookById(bookId: String) {
//        repository.getBookById(bookId) { book ->
//            _selectedBook.value = book
//        }
//    }
}