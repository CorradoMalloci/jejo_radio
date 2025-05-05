package com.example.cryptocurrencymanager.ui.crypto.list

import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity

sealed class CryptoListUiState {
    object Loading : CryptoListUiState()
    data class Success(
        val cryptocurrencies: List<CryptocurrencyEntity>,
        val favorites: Set<Int>
    ) : CryptoListUiState()
    data class Error(val message: String) : CryptoListUiState()
}
