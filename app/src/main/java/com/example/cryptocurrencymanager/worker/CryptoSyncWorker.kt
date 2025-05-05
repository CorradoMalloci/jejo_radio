package com.example.cryptocurrencymanager.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cryptocurrencymanager.data.repository.CryptoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class CryptoSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val cryptoRepository: CryptoRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Aggiorna i dati delle criptovalute
            cryptoRepository.refreshCryptocurrencies()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
