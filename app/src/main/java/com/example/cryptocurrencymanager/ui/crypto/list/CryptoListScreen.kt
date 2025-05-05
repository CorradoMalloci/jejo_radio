package com.example.cryptocurrencymanager.ui.crypto.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CryptoListScreen(
    viewModel: CryptoListViewModel = hiltViewModel(),
    onCryptoClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is CryptoListUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is CryptoListUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.cryptocurrencies) { crypto ->
                    CryptoListItem(
                        crypto = crypto,
                        isFavorite = state.favorites.contains(crypto.id),
                        onFavoriteClick = { viewModel.toggleFavorite(crypto.id, state.favorites.contains(crypto.id)) },
                        onClick = { onCryptoClick(crypto.id) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        is CryptoListUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message)
            }
        }
    }
}
