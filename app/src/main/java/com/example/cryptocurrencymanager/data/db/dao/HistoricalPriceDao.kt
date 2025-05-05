package com.example.cryptocurrencymanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocurrencymanager.data.db.entity.HistoricalPriceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricalPriceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(historicalPrices: List<HistoricalPriceEntity>)

    @Query("SELECT * FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId ORDER BY timestamp ASC")
    fun getHistoricalPricesForCryptocurrency(cryptocurrencyId: Int): Flow<List<HistoricalPriceEntity>>

    @Query("SELECT * FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId AND timeInterval = :interval AND timestamp >= :startTimestamp ORDER BY timestamp ASC")
    fun getHistoricalPricesForCryptocurrencyInRange(
        cryptocurrencyId: Int,
        interval: String,
        startTimestamp: Long
    ): Flow<List<HistoricalPriceEntity>>

    @Query("SELECT * FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId AND timestamp = :timestamp")
    suspend fun getHistoricalPriceAtTimestamp(cryptocurrencyId: Int, timestamp: Long): HistoricalPriceEntity?

    @Query("SELECT MAX(timestamp) FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId")
    suspend fun getLatestHistoricalPriceTimestamp(cryptocurrencyId: Int): Long?

    @Query("DELETE FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId")
    suspend fun deleteAllForCryptocurrency(cryptocurrencyId: Int)

    @Query("DELETE FROM historical_prices")
    suspend fun deleteAll()

    // Get historical data for specified time ranges
    @Query("SELECT * FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId AND timestamp >= :startTimestamp AND timeInterval = :interval ORDER BY timestamp ASC")
    fun getHistoricalDataForTimeRange(
        cryptocurrencyId: Int,
        startTimestamp: Long,
        interval: String
    ): Flow<List<HistoricalPriceEntity>>

    // Get daily data for the last week
    @Query("SELECT * FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId AND timestamp >= :oneWeekAgoTimestamp AND timeInterval = 'daily' ORDER BY timestamp ASC")
    fun getLastWeekData(cryptocurrencyId: Int, oneWeekAgoTimestamp: Long): Flow<List<HistoricalPriceEntity>>

    // Get hourly data for the last 24 hours
    @Query("SELECT * FROM historical_prices WHERE cryptocurrencyId = :cryptocurrencyId AND timestamp >= :oneDayAgoTimestamp AND timeInterval = 'hourly' ORDER BY timestamp ASC")
    fun getLastDayData(cryptocurrencyId: Int, oneDayAgoTimestamp: Long): Flow<List<HistoricalPriceEntity>>
}

