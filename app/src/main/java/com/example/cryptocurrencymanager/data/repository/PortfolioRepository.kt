package com.example.cryptocurrencymanager.data.repository

import com.example.cryptocurrencymanager.data.db.dao.PortfolioDao
import com.example.cryptocurrencymanager.data.db.entity.PortfolioTransactionEntity
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

class PortfolioRepository @Inject constructor(
    private val portfolioDao: PortfolioDao
) {
    suspend fun addTransaction(transaction: PortfolioTransactionEntity) =
        portfolioDao.insertTransaction(transaction)

    fun getAllTransactions(): Flow<List<PortfolioTransactionEntity>> =
        portfolioDao.getAllTransactions()

    fun getTransactionsForCrypto(cryptoId: Int): Flow<List<PortfolioTransactionEntity>> =
        portfolioDao.getTransactionsForCrypto(cryptoId)

    fun getTransactionsByType(type: TransactionType): Flow<List<PortfolioTransactionEntity>> =
        portfolioDao.getTransactionsByType(type)

    suspend fun deleteTransaction(transactionId: Long) =
        portfolioDao.deleteTransaction(transactionId)
}
