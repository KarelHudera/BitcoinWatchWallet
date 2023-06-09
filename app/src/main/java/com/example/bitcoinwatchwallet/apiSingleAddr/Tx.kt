package com.example.bitcoinwatchwallet.apiSingleAddr

import java.math.BigDecimal

data class Tx(
    val balance: BigDecimal,
    val block_height: Int?,
    val block_index: Int?,
    val double_spend: Boolean,
    val fee: Int,
    val hash: String,
    val inputs: List<Input>,
    val lock_time: Int,
    val `out`: List<Out>,
    val relayed_by: String,
    val result: BigDecimal,
    val size: Int,
    val time: Int,
    val tx_index: Long,
    val ver: Int,
    val vin_sz: Int,
    val vout_sz: Int,
    val weight: Int
)