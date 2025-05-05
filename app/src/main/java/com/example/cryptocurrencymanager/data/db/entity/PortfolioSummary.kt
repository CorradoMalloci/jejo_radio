package com.example.cryptocurrencymanager.data.db.entity

import java.math.BigDecimal

/**
 * Data class representing a summary of a user's holdings for a specific cryptocurrency
 */
data class PortfolioSummary(
    val cryptocurrencyId: Int,
    val symbol: String,
    val name: String,
    val currentPrice: BigDecimal,
    val totalQuantity: BigDecimal,
    val averageBuyPrice: BigDecimal,
    val totalInvestment: BigDecimal,
    val currentValue: BigDecimal,
    val profitLoss: BigDecimal,
    val profitLossPercentage: BigDecimal
)

/**
 * Data class representing the total portfolio value and performance
 */
data class PortfolioTotalSummary(
    val totalInvestment: BigDecimal,
    val currentValue: BigDecimal,
    val profitLoss: BigDecimal,
    val profitLossPercentage: BigDecimal,
    val holdings: List<PortfolioSummary>
)

