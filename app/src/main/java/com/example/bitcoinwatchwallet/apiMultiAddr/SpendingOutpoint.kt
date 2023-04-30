package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class SpendingOutpoint(
    @Json(name = "n")
    val n: Int,
    @Json(name = "tx_index")
    val txIndex: Long
)