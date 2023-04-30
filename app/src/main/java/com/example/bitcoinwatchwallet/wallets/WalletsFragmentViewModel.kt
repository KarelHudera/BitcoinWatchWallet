package com.example.bitcoinwatchwallet.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoinwatchwallet.ScreenState
import com.example.bitcoinwatchwallet.apiMultiAddr.ApiData
import com.example.bitcoinwatchwallet.apiMultiAddr.ApiData.getAddrInfo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WalletsFragmentViewModel: ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    init {
        walletsData()
    }

    private fun walletsData() {
        _screenState.value = ScreenState.Loading


        viewModelScope.launch {
            ApiData.getAddrInfoFlow()
                .catch { _screenState.postValue(ScreenState.Error(it)) }
                .collect{
                    if(it.body() !=null){
                        _screenState.postValue(ScreenState.Success(it.body()!!))
                    } else {
                        _screenState.postValue(ScreenState.Error(Throwable("Request failed")))
                    }
                }
        }
    }

    fun refreshFragment() {
        walletsData()
    }
}