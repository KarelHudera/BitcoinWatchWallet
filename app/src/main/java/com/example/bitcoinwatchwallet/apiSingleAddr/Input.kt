package com.example.bitcoinwatchwallet.apiSingleAddr

data class Input(
    val index: Int,
    val prev_out: PrevOut,
    val script: String,
    val sequence: Long,
    val witness: String
)