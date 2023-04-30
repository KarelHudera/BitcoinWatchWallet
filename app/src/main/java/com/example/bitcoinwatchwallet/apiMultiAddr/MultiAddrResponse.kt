package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MultiAddrResponse(
    @Json(name = "addresses")
    val addresses: List<Addresse>,
    @Json(name = "info")
    val info: Info,
    @Json(name = "recommend_include_fee")
    val recommendIncludeFee: Boolean,
    @Json(name = "txs")
    val txs: List<Tx>,
    @Json(name = "wallet")
    val wallet: Wallet
)