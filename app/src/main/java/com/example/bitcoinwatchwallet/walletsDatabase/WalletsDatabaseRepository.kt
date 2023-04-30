package com.example.bitcoinwatchwallet.walletsDatabase

import androidx.room.Room
import com.example.bitcoinwatchwallet.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

object WalletsDatabaseRepository {
    private val database: AppDatabase = Room.databaseBuilder(
        App.instance.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()

    private val walletDao = database.walletDao()

    suspend fun insertWallet(publicKey: String) {
        withContext(Dispatchers.IO) {
            val wallet = Wallet(publicKey = publicKey)
            walletDao.insertWallet(wallet)
        }
    }

    suspend fun deleteWallet(wallet: Wallet) {
        withContext(Dispatchers.IO) {
            walletDao.deleteWallet(wallet)
        }
    }

    fun getAllWallets(): Flow<List<Wallet>> {
        return walletDao.getAllWallets().flowOn(Dispatchers.IO)
    }

    suspend fun deletePublicKey(publicKey: String) {
        withContext(Dispatchers.IO){
            walletDao.deletePublicKey(publicKey)
        }
    }
}