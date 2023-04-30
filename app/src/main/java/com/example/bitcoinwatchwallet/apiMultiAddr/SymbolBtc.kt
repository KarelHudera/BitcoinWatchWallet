package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SymbolBtc(
    @Json(name = "code")
    val code: String,
    @Json(name = "conversion")
    val conversion: Int,
    @Json(name = "local")
    val local: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "symbolAppearsAfter")
    val symbolAppearsAfter: Boolean
)