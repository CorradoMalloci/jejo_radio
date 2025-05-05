package com.example.cryptocurrencymanager.ui.crypto.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.*

@Composable
fun CryptoDetailContent(
    state: CryptoDetailUiState.Success,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = state.cryptocurrency.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.cryptocurrency.symbol,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = NumberFormat.getCurrencyInstance(Locale.US)
                        .format(state.cryptocurrency.price),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Market Cap: ${NumberFormat.getCurrencyInstance(Locale.US)
                        .format(state.cryptocurrency.marketCap)}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "24h Volume: ${NumberFormat.getCurrencyInstance(Locale.US)
                        .format(state.cryptocurrency.volume24h)}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "24h Change: ${String.format("%.2f", state.cryptocurrency.percentChange24h)}%",
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (state.cryptocurrency.percentChange24h >= 0)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.error
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Price Chart placeholder
        Text(
            text = "Price History",
            style = MaterialTheme.typography.titleLarge
        )
        // Chart implementation will be added later
    }
}
