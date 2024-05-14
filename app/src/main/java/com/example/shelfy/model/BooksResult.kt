package com.example.shelfy.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class BooksResult(
    val items: List<Item>?,
    val kind: String?,
    val totalItems: Int?
): Parcelable