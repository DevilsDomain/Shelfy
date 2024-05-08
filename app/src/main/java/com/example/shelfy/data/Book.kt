package com.example.shelfy.data

import com.example.shelfy.R

data class Book(
    val imageResId: Int
)

object FakeData {
    val bookList = listOf(
        Book(imageResId = R.drawable.book_image_1),
        Book(imageResId = R.drawable.book_image_2),
        Book(imageResId = R.drawable.book_image_3),
        Book(imageResId = R.drawable.book_image_4),
        Book(imageResId = R.drawable.book_image_5)
    )
}