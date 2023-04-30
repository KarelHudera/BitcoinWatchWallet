package com.example.bitcoinwatchwallet.apiMultiAddr

import com.example.bitcoinwatchwallet.apiSingleAddr.SingleAddrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(value = "multiaddr")
    suspend fun getAddrInfo(
        @Query("active") publicKeys: String,
    ): Response <MultiAddrResponse>

    @GET(value = "rawaddr/{bitcoin_address}")
    suspend fun getAddrDetail(
        @Path("bitcoin_address") publicKey: String,
    ): Response <SingleAddrResponse>
}