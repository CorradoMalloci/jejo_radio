package com.example.cryptocurrencymanager.data.db.entity

import java.math.BigDecimal

/**
 * Entity representing portfolio performance over a specific time period
 */
data class PortfolioPerformance(
    val startTimestamp: Long,
    val endTimestamp: Long,
    val startValue: BigDecimal,
    val endValue: BigDecimal,
    val absoluteChange: BigDecimal,
    val percentageChange: BigDecimal,
    val period: String  // e.g. "24h", "7d", "30d", "1y"
)

/**
 * Entity representing portfolio asset allocation
 */
data class AssetAllocation(
    val cryptocurrencyId: Int,
    val symbol: String,
    val name: String,
    val currentValue: BigDecimal,
    val percentageOfPortfolio: BigDecimal
)

/**
 * Entity representing concentration metrics for portfolio
 */
data class PortfolioConcentration(
    val totalCryptocurrencies: Int,
    val topHoldingPercentage: BigDecimal,  // Percentage of portfolio in top holding
    val top3HoldingsPercentage: BigDecimal,  // Percentage of portfolio in top 3 holdings
    val top5HoldingsPercentage: BigDecimal,  // Percentage of portfolio in top 5 holdings
    val diversityScore: BigDecimal  // Score from 0-100 where 100 is perfectly diversified
)

/**
 * Entity representing transaction analytics for a time period
 */
data class TransactionPeriodAnalytics(
    val period: String,  // e.g. "daily", "weekly", "monthly"
    val startTimestamp: Long,
    val endTimestamp: Long,
    val buyCount: Int,
    val sellCount: Int,
    val buyVolume: BigDecimal,
    val sellVolume: BigDecimal,
    val averageBuySize: BigDecimal,
    val averageSellSize: BigDecimal,
    val buySellRatio: BigDecimal
)

/**
 * Entity representing realized and unrealized gains/losses
 */
data class ProfitLossMetrics(
    val cryptocurrencyId: Int,
    val symbol: String,
    val name: String,
    val realizedGains: BigDecimal,  // From completed sell transactions
    val unrealizedGains: BigDecimal,  // Current holdings value - cost
    val totalGains: BigDecimal,  // realized + unrealized
    val realizedROI: BigDecimal,  // Percentage return on realized investments
    val unrealizedROI: BigDecimal,  // Percentage return on current holdings
    val overallROI: BigDecimal  // Overall percentage return
)

/**
 * Entity representing investment efficiency metrics
 */
data class InvestmentEfficiency(
    val cryptocurrencyId: Int,
    val symbol: String,
    val name: String,
    val buyTimingScore: BigDecimal,  // 0-100 score based on buying at low prices
    val sellTimingScore: BigDecimal,  // 0-100 score based on selling at high prices
    val holdingPeriodAverage: Long,  // Average time between buy and sell in milliseconds
    val tradingFrequency: BigDecimal,  // Number of trades per month
    val successRate: BigDecimal  // Percentage of profitable trades
)

