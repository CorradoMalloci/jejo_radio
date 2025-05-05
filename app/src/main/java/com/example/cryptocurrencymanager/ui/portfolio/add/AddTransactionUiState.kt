package com.example.cryptocurrencymanager.ui.portfolio.add

import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity

sealed class AddTransactionUiState {
    object Loading : AddTransactionUiState()
    
    data class Success(
        val availableCryptos: List<CryptocurrencyEntity>,
        val selectedCrypto: CryptocurrencyEntity? = null,
        val quantity: String = "",
        val pricePerUnit: String = ""
    ) : AddTransactionUiState()
    
    data class Error(val message: String) : AddTransactionUiState()
    
    object TransactionComplete : AddTransactionUiState()
}
