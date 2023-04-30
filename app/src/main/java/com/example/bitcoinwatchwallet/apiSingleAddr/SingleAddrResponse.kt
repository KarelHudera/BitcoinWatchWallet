package com.example.bitcoinwatchwallet.apiSingleAddr

import java.math.BigDecimal

data class SingleAddrResponse(
    val address: String,
    val final_balance: BigDecimal,
    val hash160: String,
    val n_tx: BigDecimal,
    val n_unredeemed: BigDecimal,
    val total_received: BigDecimal,
    val total_sent: BigDecimal,
    val txs: List<Tx>
)