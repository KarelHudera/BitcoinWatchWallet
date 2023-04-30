package com.example.bitcoinwatchwallet.singleTransactions

import com.example.bitcoinwatchwallet.apiSingleAddr.SingleAddrResponse

sealed class SingleTransactionsScreenState{
    data class Success(val data: SingleAddrResponse) : SingleTransactionsScreenState()
    data class Error(val throwable: Throwable) : SingleTransactionsScreenState()
    object Loading : SingleTransactionsScreenState()
}
