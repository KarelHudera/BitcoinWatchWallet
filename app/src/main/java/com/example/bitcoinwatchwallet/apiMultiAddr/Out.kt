package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Out(
    @Json(name = "addr")
    val addr: String,
    @Json(name = "n")
    val n: Int,
    @Json(name = "script")
    val script: String,
    @Json(name = "spending_outpoints")
    val spendingOutpoints: List<SpendingOutpoint>,
    @Json(name = "spent")
    val spent: Boolean,
    @Json(name = "tx_index")
    val txIndex: Long,
    @Json(name = "type")
    val type: Int,
    @Json(name = "value")
    val value: BigDecimal
)