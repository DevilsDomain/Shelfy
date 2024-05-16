package com.example.shelfy.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShelfDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Shelf)

    @Query("SELECT * FROM shelf")
    fun getAllBooks(): LiveData<List<Shelf>>

    @Query("DELETE FROM shelf WHERE id = :id")
    fun deleteBookById(id: String)

    @Query("UPDATE shelf SET status = :status WHERE id = :id")
    fun updateBookStatus(id: String, status: String)

}
