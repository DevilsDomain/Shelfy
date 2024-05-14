package com.example.shelfy.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Pdf(
    val acsTokenLink: String?,
    val downloadLink: String?,
    val isAvailable: Boolean?
): Parcelable