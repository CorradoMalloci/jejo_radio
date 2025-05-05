package com.example.cryptocurrencymanager.data.db.converter

import androidx.room.TypeConverter
import com.example.cryptocurrencymanager.data.db.entity.TransactionType

class TransactionTypeConverter {
    @TypeConverter
    fun fromString(value: String?): TransactionType? {
        return value?.let { TransactionType.valueOf(it) }
    }

    @TypeConverter
    fun toString(transactionType: TransactionType?): String? {
        return transactionType?.name
    }
}
