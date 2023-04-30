package com.example.bitcoinwatchwallet.transactions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionsArgument(
    val addressId: String
):Parcelable
