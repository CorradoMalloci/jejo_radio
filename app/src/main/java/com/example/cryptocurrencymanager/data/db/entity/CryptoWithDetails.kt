package com.example.cryptocurrencymanager.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Represents a relationship between a cryptocurrency and its historical prices
 */
data class CryptoWithHistoricalPrices(
    @Embedded val cryptocurrency: CryptocurrencyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cryptocurrencyId"
    )
    val historicalPrices: List<HistoricalPriceEntity>
)

/**
 * Represents a relationship between a cryptocurrency and its portfolio transactions
 */
data class CryptoWithTransactions(
    @Embedded val cryptocurrency: CryptocurrencyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cryptocurrencyId"
    )
    val transactions: List<PortfolioTransactionEntity>
)

