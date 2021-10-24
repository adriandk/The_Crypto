package com.adrian.thecrypto.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Crypto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val price: String,
    val percent: String,
    val volume: String,
    val description: String,
    val favorite: Boolean
): Parcelable