package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "conversion")
    val conversion: Int,
    @Json(name = "latest_block")
    val latestBlock: LatestBlock,
    @Json(name = "nconnected")
    val nconnected: Int,
    @Json(name = "symbol_btc")
    val symbolBtc: SymbolBtc,
    @Json(name = "symbol_local")
    val symbolLocal: SymbolLocal
)