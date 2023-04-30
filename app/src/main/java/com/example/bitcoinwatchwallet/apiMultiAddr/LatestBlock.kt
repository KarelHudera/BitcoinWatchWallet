package com.example.bitcoinwatchwallet.apiMultiAddr


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestBlock(
    @Json(name = "block_index")
    val blockIndex: Int,
    @Json(name = "hash")
    val hash: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "time")
    val time: Int
)