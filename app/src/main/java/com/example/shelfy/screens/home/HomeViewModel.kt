package com.example.shelfy.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.Shelf
import com.example.shelfy.db.ShelfRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val database = AppDatabase.getDatabase(application)
    val repository = ShelfRepository(database.shelfDao())
    val allBooks: LiveData<List<Shelf>> = repository.allBooks

}