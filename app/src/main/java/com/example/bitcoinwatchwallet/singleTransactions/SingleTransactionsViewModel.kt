package com.example.bitcoinwatchwallet.singleTransactions

import androidx.lifecycle.*
import com.example.bitcoinwatchwallet.ScreenState
import com.example.bitcoinwatchwallet.apiMultiAddr.ApiData
import com.example.bitcoinwatchwallet.apiMultiAddr.ApiData.getAddrDetail
import com.example.bitcoinwatchwallet.apiSingleAddr.SingleAddrResponse
import com.example.bitcoinwatchwallet.walletsDatabase.WalletsDatabaseRepository
import kotlinx.coroutines.launch

class SingleTransactionsViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val addressId = SingleTransactionsFragmentArgs.fromSavedStateHandle(savedStateHandle).addressId

    private val _screenState = MutableLiveData<SingleTransactionsScreenState>()
    val screenState: LiveData<SingleTransactionsScreenState> = _screenState


    init {
        walletsData()
    }

    private fun walletsData() {
        _screenState.value = SingleTransactionsScreenState.Loading

        viewModelScope.launch {
            try {
                val SingleAddrResponse = getAddrDetail(addressId).body()!!
                _screenState.postValue(SingleTransactionsScreenState.Success(SingleAddrResponse))
            } catch (exception: Exception) {
                _screenState.postValue(SingleTransactionsScreenState.Error(exception))
            }
        }
    }

    fun refreshFragment() {
        walletsData()
    }

    fun deletePublicKey(publicKey: String){
        viewModelScope.launch {
            WalletsDatabaseRepository.deletePublicKey(addressId)
        }
    }
}