package com.example.cryptocurrencymanager.data.repository

import com.example.cryptocurrencymanager.data.db.dao.CryptocurrencyDao
import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity
import com.example.cryptocurrencymanager.data.network.api.CoinMarketCapService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val cryptocurrencyDao: CryptocurrencyDao,
    private val apiService: CoinMarketCapService
) {
    fun getAllCryptocurrencies(): Flow<List<CryptocurrencyEntity>> =
        cryptocurrencyDao.getAllCryptocurrencies()

    suspend fun refreshCryptocurrencies() {
        try {
            val response = apiService.getLatestListings()
            val entities = response.data.map { crypto ->
                CryptocurrencyEntity(
                    id = crypto.id,
                    name = crypto.name,
                    symbol = crypto.symbol,
                    price = crypto.quote.usd.price,
                    marketCap = crypto.quote.usd.marketCap,
                    volume24h = crypto.quote.usd.volume24h,
                    percentChange24h = crypto.quote.usd.percentChange24h,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            cryptocurrencyDao.insertCryptocurrencies(entities)
        } catch (e: Exception) {
            // Handle error
            throw e
        }
    }
}
