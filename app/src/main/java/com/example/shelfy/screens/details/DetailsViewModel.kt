package com.example.shelfy.screens.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.Shelf
import com.example.shelfy.db.ShelfRepository


class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedBook = MutableLiveData<Shelf?>()
    val selectedBook: LiveData<Shelf?> = _selectedBook

    private val database = AppDatabase.getDatabase(application)
    private val repository = ShelfRepository(database.shelfDao())


    fun updateBookStatus(bookId: String, newStatus: String) {
        repository.updateBookStatus(bookId, newStatus)
    }
}