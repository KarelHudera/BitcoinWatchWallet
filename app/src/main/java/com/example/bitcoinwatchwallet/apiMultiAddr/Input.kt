package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Input(
    @Json(name = "index")
    val index: Int,
    @Json(name = "prev_out")
    val prevOut: PrevOut,
    @Json(name = "script")
    val script: String,
    @Json(name = "sequence")
    val sequence: Long,
    @Json(name = "witness")
    val witness: String
)