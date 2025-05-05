package com.example.cryptocurrencymanager.ui.crypto.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencymanager.data.repository.CryptoRepository
import com.example.cryptocurrencymanager.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CryptoListUiState>(CryptoListUiState.Loading)
    val uiState: StateFlow<CryptoListUiState> = _uiState

    init {
        loadCryptocurrencies()
    }

    fun loadCryptocurrencies() {
        viewModelScope.launch {
            try {
                _uiState.value = CryptoListUiState.Loading
                cryptoRepository.refreshCryptocurrencies()
                // La lista verrà aggiornata automaticamente tramite Flow
            } catch (e: Exception) {
                _uiState.value = CryptoListUiState.Error(e.message ?: "Error loading cryptocurrencies")
            }
        }
    }

    fun toggleFavorite(cryptoId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                if (isFavorite) {
                    favoriteRepository.removeFavorite(cryptoId)
                } else {
                    favoriteRepository.addFavorite(cryptoId)
                }
            } catch (e: Exception) {
                _uiState.value = CryptoListUiState.Error(e.message ?: "Error updating favorite")
            }
        }
    }
}
