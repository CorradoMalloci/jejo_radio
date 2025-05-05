package com.example.cryptocurrencymanager.data.network.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinMarketCapApiConfig {
    private const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    private const val API_KEY_HEADER = "X-CMC_PRO_API_KEY"

    private val apiKeyInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader(API_KEY_HEADER, BuildConfig.COINMARKETCAP_API_KEY)
            .build()
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    val service: CoinMarketCapService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoinMarketCapService::class.java)
}
