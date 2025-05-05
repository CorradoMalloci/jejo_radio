package com.example.cryptocurrencymanager.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMarketCapService {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getLatestListings(
        @Query("start") start: Int = 1,
        @Query("limit") limit: Int = 100,
        @Query("convert") convert: String = "USD"
    ): LatestListingsResponse
}
