package com.example.cryptocurrencymanager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cryptocurrencymanager.data.db.converter.BigDecimalConverter
import com.example.cryptocurrencymanager.data.db.converter.TransactionTypeConverter
import com.example.cryptocurrencymanager.data.db.dao.*
import com.example.cryptocurrencymanager.data.db.entity.*

@Database(
    entities = [
        CryptocurrencyEntity::class,
        PortfolioTransactionEntity::class,
        FavoriteEntity::class
    ],
    version = 1
)
@TypeConverters(
    BigDecimalConverter::class,
    TransactionTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptocurrencyDao(): CryptocurrencyDao
    abstract fun portfolioDao(): PortfolioDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "crypto_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
