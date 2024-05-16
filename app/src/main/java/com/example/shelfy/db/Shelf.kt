package com.example.shelfy.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shelf")
data class Shelf(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "status")
    var status: String? = "New",
    @ColumnInfo(name = "finishedAt")
    val finishedAt: Long? = null
)