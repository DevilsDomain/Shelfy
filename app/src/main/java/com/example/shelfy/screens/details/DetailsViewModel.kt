package com.example.shelfy.screens.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.Shelf
import com.example.shelfy.db.ShelfRepository


class DetailsViewModel(application: Application, private val repository: ShelfRepository) : AndroidViewModel(application) {
    private val _selectedBook = MutableLiveData<Shelf?>()
    val selectedBook: LiveData<Shelf?> = _selectedBook

    private val database = AppDatabase.getDatabase(application)

    fun updateBookStatus(id: String, status: String) {
        repository.updateBookStatus(id, status)
        if (status == "Finished") {
            repository.updateFinishedDate(id, System.currentTimeMillis())
        } else {
            repository.updateFinishedDate(id, null)
        }
    }

    fun updateBookRating(bookId: String, rating: Int) {
        repository.updateBookRating(bookId, rating)
    }

    fun updateBookNotes(bookId: String, notes: String) {
        repository.updateBookNotes(bookId, notes)
    }


}