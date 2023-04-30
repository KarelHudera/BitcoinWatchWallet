package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Tx(
    @Json(name = "balance")
    val balance: BigDecimal,
    @Json(name = "block_height")
    val blockHeight: Int?,
    @Json(name = "block_index")
    val blockIndex: Int?,
    @Json(name = "double_spend")
    val doubleSpend: Boolean,
    @Json(name = "fee")
    val fee: Int,
    @Json(name = "hash")
    val hash: String,
    @Json(name = "inputs")
    val inputs: List<Input>,
    @Json(name = "lock_time")
    val lockTime: Int,
    @Json(name = "out")
    val `out`: List<Out>,
    @Json(name = "relayed_by")
    val relayedBy: String,
    @Json(name = "result")
    val result: BigDecimal,
    @Json(name = "size")
    val size: Int,
    @Json(name = "time")
    val time: Int,
    @Json(name = "tx_index")
    val txIndex: Long,
    @Json(name = "ver")
    val ver: Int,
    @Json(name = "vin_sz")
    val vinSz: Int,
    @Json(name = "vout_sz")
    val voutSz: Int,
    @Json(name = "weight")
    val weight: Int
)