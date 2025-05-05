package com.example.cryptocurrencymanager.data.repository

import com.example.cryptocurrencymanager.data.db.dao.FavoriteDao
import com.example.cryptocurrencymanager.data.db.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    suspend fun addFavorite(cryptoId: Int) {
        val favorite = FavoriteEntity(
            cryptocurrencyId = cryptoId,
            timestamp = System.currentTimeMillis()
        )
        favoriteDao.addFavorite(favorite)
    }

    suspend fun removeFavorite(cryptoId: Int) =
        favoriteDao.removeFavorite(cryptoId)

    fun getAllFavorites(): Flow<List<FavoriteEntity>> =
        favoriteDao.getAllFavorites()

    fun isFavorite(cryptoId: Int): Flow<Boolean> =
        favoriteDao.isFavorite(cryptoId)
}
