package com.example.cryptocurrencymanager.data.network.api.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class LatestListingsResponse(
    val status: Status,
    val data: List<CryptocurrencyData>
) {
    data class Status(
        @SerializedName("timestamp")
        val timestamp: String,
        @SerializedName("error_code")
        val errorCode: Int,
        @SerializedName("error_message")
        val errorMessage: String?
    )

    data class CryptocurrencyData(
        val id: Int,
        val name: String,
        val symbol: String,
        val quote: Quote
    ) {
        data class Quote(
            @SerializedName("USD")
            val usd: UsdData
        ) {
            data class UsdData(
                val price: BigDecimal,
                @SerializedName("market_cap")
                val marketCap: BigDecimal,
                @SerializedName("volume_24h")
                val volume24h: BigDecimal,
                @SerializedName("percent_change_24h")
                val percentChange24h: Double
            )
        }
    }
}
