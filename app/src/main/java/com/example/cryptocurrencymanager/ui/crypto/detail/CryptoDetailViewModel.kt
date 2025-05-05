package com.example.cryptocurrencymanager.ui.crypto.detail

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
class CryptoDetailViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CryptoDetailUiState>(CryptoDetailUiState.Loading)
    val uiState: StateFlow<CryptoDetailUiState> = _uiState

    fun loadCryptoDetails(cryptoId: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = CryptoDetailUiState.Loading
                // Implementation will be added
            } catch (e: Exception) {
                _uiState.value = CryptoDetailUiState.Error(e.message ?: "Error loading crypto details")
            }
        }
    }
}
