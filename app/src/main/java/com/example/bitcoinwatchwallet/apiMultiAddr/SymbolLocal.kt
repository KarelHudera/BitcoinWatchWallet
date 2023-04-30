package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SymbolLocal(
    @Json(name = "code")
    val code: String,
    @Json(name = "conversion")
    val conversion: Double,
    @Json(name = "local")
    val local: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "symbolAppearsAfter")
    val symbolAppearsAfter: Boolean
)