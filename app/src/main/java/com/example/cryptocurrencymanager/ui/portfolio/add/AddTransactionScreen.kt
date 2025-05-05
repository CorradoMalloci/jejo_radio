package com.example.cryptocurrencymanager.ui.portfolio.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Transaction") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is AddTransactionUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    CircularProgressIndicator()
                }
            }
            is AddTransactionUiState.Success -> {
                AddTransactionContent(
                    state = state,
                    onAddTransaction = { cryptoId, type, quantity, price ->
                        viewModel.addTransaction(cryptoId, type, quantity, price)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
            is AddTransactionUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Text(state.message)
                }
            }
            AddTransactionUiState.TransactionComplete -> {
                LaunchedEffect(Unit) {
                    onNavigateBack()
                }
            }
        }
    }
}
