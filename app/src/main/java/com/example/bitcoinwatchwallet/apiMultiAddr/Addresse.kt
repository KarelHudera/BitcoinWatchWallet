package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Addresse(
    @Json(name = "address")
    val address: String,
    @Json(name = "final_balance")
    val finalBalance: BigDecimal,
    @Json(name = "n_tx")
    val nTx: BigDecimal,
    @Json(name = "total_received")
    val totalReceived: BigDecimal,
    @Json(name = "total_sent")
    val totalSent: BigDecimal,

    //
    var isFavorite: Boolean = false

)