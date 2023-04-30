package com.example.bitcoinwatchwallet.addressInputDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoinwatchwallet.walletsDatabase.WalletsDatabaseRepository
import kotlinx.coroutines.launch

class AddressInputViewModel: ViewModel() {

    fun insertWallet(publicKey: String){
        viewModelScope.launch {
            WalletsDatabaseRepository.insertWallet(publicKey)
        }
    }

}
