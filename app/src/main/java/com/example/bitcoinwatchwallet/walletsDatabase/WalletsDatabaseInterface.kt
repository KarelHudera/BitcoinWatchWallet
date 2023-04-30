package com.example.bitcoinwatchwallet.walletsDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {
    @Insert
    suspend fun insertWallet(wallet: Wallet)

    @Delete
    suspend fun deleteWallet(wallet: Wallet)

//    @Query("SELECT * FROM wallet")
//    suspend fun deletePublicKey(publicKey: String)

    @Query("SELECT * FROM wallet")
    fun getAllWallets(): Flow<List<Wallet>>

    @Query("DELETE FROM wallet WHERE publicKey=:publicKey")
    suspend fun deletePublicKey(publicKey: String)

}
