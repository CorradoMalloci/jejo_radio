package com.example.cryptocurrencymanager.ui.crypto.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cryptocurrencymanager.data.db.entity.CryptocurrencyEntity

@Composable
fun CryptoListItem(
    crypto: CryptocurrencyEntity,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = crypto.name)
            Text(text = crypto.symbol)
        }
    }
}
