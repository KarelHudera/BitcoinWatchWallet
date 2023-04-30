package com.example.bitcoinwatchwallet

import com.example.bitcoinwatchwallet.apiMultiAddr.MultiAddrResponse

sealed class ScreenState {
    data class Success(val data: MultiAddrResponse) : ScreenState()
    data class Error(val throwable: Throwable) : ScreenState()
    object Loading : ScreenState()
}
