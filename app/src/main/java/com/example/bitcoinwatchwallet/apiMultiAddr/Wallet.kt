package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Wallet(
    @Json(name = "final_balance")
    val finalBalance: BigDecimal,
    @Json(name = "n_tx")
    val nTx: BigDecimal,
    @Json(name = "n_tx_filtered")
    val nTxFiltered: BigDecimal,
    @Json(name = "total_received")
    val totalReceived: BigDecimal,
    @Json(name = "total_sent")
    val totalSent: BigDecimal
)