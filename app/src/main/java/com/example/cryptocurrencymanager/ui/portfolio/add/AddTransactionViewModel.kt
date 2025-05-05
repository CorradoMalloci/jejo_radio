package com.example.cryptocurrencymanager.ui.portfolio.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencymanager.data.repository.CryptoRepository
import com.example.cryptocurrencymanager.data.repository.PortfolioRepository
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    private val portfolioRepository: PortfolioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddTransactionUiState>(AddTransactionUiState.Loading)
    val uiState: StateFlow<AddTransactionUiState> = _uiState

    init {
        loadCryptocurrencies()
    }

    private fun loadCryptocurrencies() {
        viewModelScope.launch {
            try {
                _uiState.value = AddTransactionUiState.Loading
                // Load available cryptocurrencies
                cryptoRepository.getAllCryptocurrencies().collect { cryptos ->
                    _uiState.value = AddTransactionUiState.Success(
                        availableCryptos = cryptos
                    )
                }
            } catch (e: Exception) {
                _uiState.value = AddTransactionUiState.Error(e.message ?: "Error loading cryptocurrencies")
            }
        }
    }

    fun addTransaction(
        cryptoId: Int,
        type: TransactionType,
        quantity: BigDecimal,
        pricePerUnit: BigDecimal
    ) {
        viewModelScope.launch {
            try {
                portfolioRepository.addTransaction(
                    cryptoId = cryptoId,
                    type = type,
                    quantity = quantity,
                    pricePerUnit = pricePerUnit
                )
                _uiState.value = AddTransactionUiState.TransactionComplete
            } catch (e: Exception) {
                _uiState.value = AddTransactionUiState.Error(e.message ?: "Error adding transaction")
            }
        }
    }
}
