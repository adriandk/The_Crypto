package com.adrian.thecrypto.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.thecrypto.core.data.source.local.entity.CryptoEntity

@Database(entities = [CryptoEntity::class], version = 1, exportSchema = false)
abstract class CryptoDatabase: RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}