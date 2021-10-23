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
    var symbol: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "price")
    var price: String,

    @ColumnInfo(name = "percentage")
    var percent: String,

    @ColumnInfo(name = "volume")
    var volume: String,

    @ColumnInfo(name = "description")
    var desc: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
)