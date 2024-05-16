package com.example.shelfy.db

import androidx.lifecycle.LiveData

class ShelfRepository(private val shelfDao: ShelfDao) {
    val allBooks: LiveData<List<Shelf>> = shelfDao.getAllBooks()

     fun insertBook(book: Shelf) {
        AppDatabase.databaseWriteExecutor.execute{
            shelfDao.insert(book)
        }
    }
    fun updateBookStatus(id: String, status: String) {
        AppDatabase.databaseWriteExecutor.execute {
            shelfDao.updateBookStatus(id, status)
        }
    }

    fun getBookById(id: String, callback: (Shelf?) -> Unit) {
        // Fetch book by ID asynchronously
        AppDatabase.databaseWriteExecutor.execute {
            val book = shelfDao.getBookById(id)
            callback(book)
        }
    }
}
