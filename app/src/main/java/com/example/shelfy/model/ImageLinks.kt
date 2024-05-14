package com.example.shelfy.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
): Parcelable