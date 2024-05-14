package com.example.shelfy.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class RetailPriceX(
    val amount: Double?,
    val currencyCode: String?
): Parcelable