package com.example.cryptocurrencymanager.ui.crypto.detail

import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity

sealed class CryptoDetailUiState {
    object Loading : CryptoDetailUiState()
    data class Success(
        val cryptocurrency: CryptocurrencyEntity,
        val isFavorite: Boolean,
        val priceHistory: List<PricePoint> = emptyList()
    ) : CryptoDetailUiState()
    data class Error(val message: String) : CryptoDetailUiState()
}

data class PricePoint(
    val timestamp: Long,
    val price: Double
)
