package com.example.cryptocurrencymanager.di

import android.content.Context
import androidx.room.Room
import com.example.cryptocurrencymanager.data.db.AppDatabase
import com.example.cryptocurrencymanager.data.network.api.CoinMarketCapApiConfig
import com.example.cryptocurrencymanager.data.network.api.CoinMarketCapService
import com.example.cryptocurrencymanager.data.repository.CryptoRepository
import com.example.cryptocurrencymanager.data.repository.FavoriteRepository
import com.example.cryptocurrencymanager.data.repository.PortfolioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "crypto_manager_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinMarketCapService(): CoinMarketCapService {
        return CoinMarketCapApiConfig.service
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(
        database: AppDatabase,
        apiService: CoinMarketCapService
    ): CryptoRepository {
        return CryptoRepository(
            cryptocurrencyDao = database.cryptocurrencyDao(),
            apiService = apiService
        )
    }

    @Provides
    @Singleton
    fun providePortfolioRepository(
        database: AppDatabase
    ): PortfolioRepository {
        return PortfolioRepository(
            portfolioDao = database.portfolioDao()
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        database: AppDatabase
    ): FavoriteRepository {
        return FavoriteRepository(
            favoriteDao = database.favoriteDao()
        )
    }
}
