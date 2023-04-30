package com.example.bitcoinwatchwallet.walletsDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Wallet::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}