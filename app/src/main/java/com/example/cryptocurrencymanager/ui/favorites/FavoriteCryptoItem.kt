package com.example.cryptocurrencymanager.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity
import java.text.NumberFormat
import java.util.*

@Composable
fun FavoriteCryptoItem(
    crypto: CryptocurrencyEntity,
    onRemoveClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = crypto.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = crypto.symbol,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = NumberFormat.getCurrencyInstance(Locale.US)
                        .format(crypto.price),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${String.format("%.2f", crypto.percentChange24h)}%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (crypto.percentChange24h >= 0)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.error
                )
            }
            
            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Remove from favorites",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
