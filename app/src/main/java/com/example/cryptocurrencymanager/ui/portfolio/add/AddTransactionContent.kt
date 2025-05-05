package com.example.cryptocurrencymanager.ui.portfolio.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionContent(
    state: AddTransactionUiState.Success,
    onAddTransaction: (Int, TransactionType, BigDecimal, BigDecimal) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCryptoId by remember { mutableStateOf<Int?>(null) }
    var selectedType by remember { mutableStateOf(TransactionType.BUY) }
    var quantity by remember { mutableStateOf("") }
    var pricePerUnit by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Cryptocurrency Dropdown
        ExposedDropdownMenuBox(
            expanded = false,
            onExpandedChange = { }
        ) {
            TextField(
                value = state.selectedCrypto?.name ?: "Select Cryptocurrency",
                onValueChange = { },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = false,
                onDismissRequest = { }
            ) {
                state.availableCryptos.forEach { crypto ->
                    DropdownMenuItem(
                        text = { Text("${crypto.name} (${crypto.symbol})") },
                        onClick = { selectedCryptoId = crypto.id }
                    )
                }
            }
        }

        // Transaction Type Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TransactionType.values().forEach { type ->
                FilterChip(
                    selected = selectedType == type,
                    onClick = { selectedType = type },
                    label = { Text(type.name) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Quantity Input
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        // Price Input
        TextField(
            value = pricePerUnit,
            onValueChange = { pricePerUnit = it },
            label = { Text("Price per unit") },
            modifier = Modifier.fillMaxWidth()
        )

        // Add Transaction Button
        Button(
            onClick = {
                selectedCryptoId?.let { cryptoId ->
                    try {
                        val quantityBigDecimal = BigDecimal(quantity)
                        val priceBigDecimal = BigDecimal(pricePerUnit)
                        onAddTransaction(cryptoId, selectedType, quantityBigDecimal, priceBigDecimal)
                    } catch (e: NumberFormatException) {
                        // Handle invalid number format
                    }
                }
            },
            enabled = selectedCryptoId != null && quantity.isNotEmpty() && pricePerUnit.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Transaction")
        }
    }
}
