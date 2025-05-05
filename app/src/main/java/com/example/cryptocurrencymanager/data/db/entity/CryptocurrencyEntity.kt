package com.example.cryptocurrencymanager.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "cryptocurrencies")
data class CryptocurrencyEntity(
    @PrimaryKey
    val id: Int,
    val symbol: String,
    val name: String,
    val price: BigDecimal,
    val marketCap: BigDecimal,
    val volume24h: BigDecimal,
    val percentChange24h: Double,
    val lastUpdated: Long
)
