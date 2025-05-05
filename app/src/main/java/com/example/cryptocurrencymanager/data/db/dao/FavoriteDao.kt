package com.example.cryptocurrencymanager.data.db.dao

import androidx.room.*
import com.example.cryptocurrencymanager.data.db.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE cryptocurrencyId = :cryptoId")
    suspend fun removeFavorite(cryptoId: Int)

    @Query("SELECT * FROM favorites ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE cryptocurrencyId = :cryptoId)")
    fun isFavorite(cryptoId: Int): Flow<Boolean>
}
