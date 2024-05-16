package com.example.shelfy.db

import androidx.lifecycle.LiveData

class ShelfRepository(private val shelfDao: ShelfDao) {
    val allBooks: LiveData<List<Shelf>> = shelfDao.getAllBooks()

     fun insertBook(book: Shelf) {
        AppDatabase.databaseWriteExecutor.execute{
            shelfDao.insert(book)
        }
    }
}
