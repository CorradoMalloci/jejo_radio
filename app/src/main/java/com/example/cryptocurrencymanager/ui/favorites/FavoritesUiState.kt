package com.example.cryptocurrencymanager.ui.favorites

import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity

sealed class FavoritesUiState {
    object Loading : FavoritesUiState()
    
    data class Success(
        val favoriteCryptos: List<CryptocurrencyEntity>
    ) : FavoritesUiState()
    
    data class Error(val message: String) : FavoritesUiState()
}
