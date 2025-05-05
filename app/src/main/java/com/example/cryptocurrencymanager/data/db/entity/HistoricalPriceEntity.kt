package com.example.cryptocurrencymanager.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import java.math.BigDecimal

/**
 * Entity representing historical price data for a cryptocurrency
 */
@Entity(
    tableName = "historical_prices",
    primaryKeys = ["cryptocurrencyId", "timestamp"],
    foreignKeys = [
        ForeignKey(
            entity = CryptocurrencyEntity::class,
            parentColumns = ["id"],
            childColumns = ["cryptocurrencyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("cryptocurrencyId")
    ]
)
data class HistoricalPriceEntity(
    val cryptocurrencyId: Int,
    val timestamp: Long,
    val price: BigDecimal,
    val volume: BigDecimal,
    val marketCap: BigDecimal,
    val timeInterval: String // e.g., "hourly", "daily"
)

