package com.example.cryptocurrencymanager.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.math.BigDecimal

@Entity(
    tableName = "portfolio_transactions",
    foreignKeys = [
        ForeignKey(
            entity = CryptocurrencyEntity::class,
            parentColumns = ["id"],
            childColumns = ["cryptocurrencyId"]
        )
    ]
)
data class PortfolioTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cryptocurrencyId: Int,
    val type: TransactionType,
    val quantity: BigDecimal,
    val pricePerUnit: BigDecimal,
    val transactionValue: BigDecimal,
    val timestamp: Long
)
