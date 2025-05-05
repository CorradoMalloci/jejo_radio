package com.example.cryptocurrencymanager.ui.portfolio

import com.example.cryptocurrencymanager.data.db.entity.PortfolioTransactionEntity
import java.math.BigDecimal

sealed class PortfolioUiState {
    object Loading : PortfolioUiState()
    
    data class Success(
        val totalValue: BigDecimal,
        val totalProfit: BigDecimal,
        val profitPercentage: Double,
        val holdings: List<PortfolioHolding>,
        val recentTransactions: List<PortfolioTransactionEntity>
    ) : PortfolioUiState()
    
    data class Error(val message: String) : PortfolioUiState()
}

data class PortfolioHolding(
    val cryptoId: Int,
    val symbol: String,
    val name: String,
    val quantity: BigDecimal,
    val currentPrice: BigDecimal,
    val totalValue: BigDecimal,
    val averageBuyPrice: BigDecimal,
    val profitLoss: BigDecimal,
    val profitLossPercentage: Double
)
