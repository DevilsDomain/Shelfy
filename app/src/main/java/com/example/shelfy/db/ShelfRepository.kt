package com.example.shelfy.db


import androidx.lifecycle.LiveData

class ShelfRepository(private val shelfDao: ShelfDao) {
    val allBooks: LiveData<List<Shelf>> = shelfDao.getAllBooks()
    val newBooks: LiveData<List<Shelf>> = shelfDao.getBooksByStatus("New")
    val readingBooks: LiveData<List<Shelf>> = shelfDao.getBooksByStatus("Reading")
    val finishedBooks: LiveData<List<Shelf>> = shelfDao.getBooksByStatus("Finished")



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
    fun deleteBookById(id: String) {
        AppDatabase.databaseWriteExecutor.execute {
            shelfDao.deleteBookById(id)
        }
    }

    fun updateBookRating(id: String, rating: Int) {
        AppDatabase.databaseWriteExecutor.execute {
            shelfDao.updateBookRating(id, rating)
        }
    }


    fun updateBookNotes(id: String, notes: String) {
        AppDatabase.databaseWriteExecutor.execute {
            shelfDao.updateBookNotes(id, notes)
        }
    }


}