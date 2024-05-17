package com.example.shelfy.screens.home.tabs.tab_new

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.Shelf
import com.example.shelfy.db.ShelfRepository

class TabNewViewModel(application: Application) : AndroidViewModel(application) {
    val database = AppDatabase.getDatabase(application)
    val repository = ShelfRepository(database.shelfDao())
    val newBooks: LiveData<List<Shelf>> = repository.newBooks
}