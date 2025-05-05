package com.example.cryptocurrencymanager.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites",
    foreignKeys = [
        ForeignKey(
            entity = CryptocurrencyEntity::class,
            parentColumns = ["id"],
            childColumns = ["cryptocurrencyId"]
        )
    ]
)
data class FavoriteEntity(
    @PrimaryKey
    val cryptocurrencyId: Int,
    val timestamp: Long
)
