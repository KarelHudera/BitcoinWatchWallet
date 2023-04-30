package com.example.bitcoinwatchwallet.walletsDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val publicKey: String,
    var isFavorite: Boolean = false
    )




