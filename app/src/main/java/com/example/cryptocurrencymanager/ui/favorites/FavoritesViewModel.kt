package com.example.cryptocurrencymanager.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencymanager.data.repository.CryptoRepository
import com.example.cryptocurrencymanager.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            try {
                // Combine favorite cryptos with their current data
                combine(
                    favoriteRepository.getAllFavorites(),
                    cryptoRepository.getAllCryptocurrencies()
                ) { favorites, allCryptos ->
                    val favoriteCryptos = allCryptos.filter { crypto ->
                        favorites.any { it.cryptocurrencyId == crypto.id }
                    }
                    FavoritesUiState.Success(favoriteCryptos)
                }.catch { e ->
                    _uiState.value = FavoritesUiState.Error(e.message ?: "Error loading favorites")
                }.collect { state ->
                    _uiState.value = state
                }
            } catch (e: Exception) {
                _uiState.value = FavoritesUiState.Error(e.message ?: "Error loading favorites")
            }
        }
    }

    fun removeFavorite(cryptoId: Int) {
        viewModelScope.launch {
            try {
                favoriteRepository.removeFavorite(cryptoId)
            } catch (e: Exception) {
                _uiState.value = FavoritesUiState.Error(e.message ?: "Error removing favorite")
            }
        }
    }
}
