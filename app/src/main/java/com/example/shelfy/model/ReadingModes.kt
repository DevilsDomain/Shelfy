package com.example.shelfy.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ReadingModes(
    val image: Boolean?,
    val text: Boolean?
): Parcelable