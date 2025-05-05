package com.example.cryptocurrencymanager.ui.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencymanager.data.repository.PortfolioRepository
import com.example.cryptocurrencymanager.data.db.entity.PortfolioTransactionEntity
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioRepository: PortfolioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PortfolioUiState>(PortfolioUiState.Loading)
    val uiState: StateFlow<PortfolioUiState> = _uiState

    init {
        loadPortfolio()
    }

    private fun loadPortfolio() {
        viewModelScope.launch {
            try {
                _uiState.value = PortfolioUiState.Loading
                // Implementation will be added
            } catch (e: Exception) {
                _uiState.value = PortfolioUiState.Error(e.message ?: "Error loading portfolio")
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
                val transaction = PortfolioTransactionEntity(
                    cryptocurrencyId = cryptoId,
                    type = type,
                    quantity = quantity,
                    pricePerUnit = pricePerUnit,
                    transactionValue = quantity.multiply(pricePerUnit),
                    timestamp = System.currentTimeMillis()
                )
                portfolioRepository.addTransaction(transaction)
            } catch (e: Exception) {
                _uiState.value = PortfolioUiState.Error(e.message ?: "Error adding transaction")
            }
        }
    }
}
