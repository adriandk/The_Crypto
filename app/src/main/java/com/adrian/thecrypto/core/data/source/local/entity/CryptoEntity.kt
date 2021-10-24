package com.adrian.thecrypto.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CryptoEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "symbol")
    var symbol: String?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "price")
    var price: Double?,

    @ColumnInfo(name = "percentage")
    var percent: Double?,

    @ColumnInfo(name = "volume")
    var volume: Double?,

    @ColumnInfo(name = "ath")
    var ath: Double?,

    @ColumnInfo(name = "marketcap")
    var marketcap: Double?,

    @ColumnInfo(name = "high")
    var high: Double?,

    @ColumnInfo(name = "low")
    var low: Double?,

    @ColumnInfo(name = "supply")
    var supply: Double?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
)