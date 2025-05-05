package com.example.cryptocurrencymanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptocurrencyDao {
    @Query("SELECT * FROM cryptocurrencies")
    fun getAllCryptocurrencies(): Flow<List<CryptocurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrency(crypto: CryptocurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrencies(cryptos: List<CryptocurrencyEntity>)

    @Query("SELECT * FROM cryptocurrencies WHERE id = :id")
    fun getCryptocurrencyById(id: Int): Flow<CryptocurrencyEntity?>

    @Query("SELECT * FROM cryptocurrencies WHERE symbol = :symbol")
    fun getCryptocurrencyBySymbol(symbol: String): Flow<CryptocurrencyEntity?>
}
