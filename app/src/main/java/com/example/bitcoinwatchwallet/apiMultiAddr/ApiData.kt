package com.example.bitcoinwatchwallet.apiMultiAddr

import com.example.bitcoinwatchwallet.apiSingleAddr.SingleAddrResponse
import com.example.bitcoinwatchwallet.walletsDatabase.WalletsDatabaseRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import cz.sazka.sazkabet.sgd.moshiadapters.BigDecimalAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val baseUrl = "https://blockchain.info/"

object ApiData {
    private val apiInterface by lazy {
        val moshi = Moshi.Builder()
            .add(BigDecimalAdapter)
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    suspend fun getAddrInfo(publicKeys: String): Response <MultiAddrResponse> {
        return apiInterface.getAddrInfo(publicKeys)
    }

    suspend fun getAddrDetail(publicKey: String): Response <SingleAddrResponse> {
        return apiInterface.getAddrDetail(publicKey)
    }

    fun getAddrInfoFlow(): Flow<Response<MultiAddrResponse>> {
        return WalletsDatabaseRepository.getAllWallets()

            .map { wallets ->
                val publicKeys = wallets.joinToString(separator = "|") { it.publicKey }
                apiInterface.getAddrInfo(publicKeys)
            }
    }
}