package com.example.cryptocurrencymanager.data.db.dao

import androidx.room.*
import com.example.cryptocurrencymanager.data.db.entity.PortfolioTransactionEntity
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface PortfolioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: PortfolioTransactionEntity): Long

    @Query("SELECT * FROM portfolio_transactions WHERE id = :transactionId")
    fun getTransactionById(transactionId: Long): Flow<PortfolioTransactionEntity?>

    @Query("SELECT * FROM portfolio_transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<PortfolioTransactionEntity>>
    
    @Query("SELECT * FROM portfolio_transactions WHERE cryptocurrencyId = :cryptoId ORDER BY timestamp DESC")
    fun getTransactionsForCrypto(cryptoId: Int): Flow<List<PortfolioTransactionEntity>>
    
    @Query("SELECT * FROM portfolio_transactions WHERE type = :type ORDER BY timestamp DESC")
    fun getTransactionsByType(type: TransactionType): Flow<List<PortfolioTransactionEntity>>
}
