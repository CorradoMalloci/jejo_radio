package com.example.cryptocurrencymanager.data.db.converter

import androidx.room.TypeConverter
import com.example.cryptocurrencymanager.data.db.entity.TransactionType

/**
 * Type converters for enums used in the database
 */
class EnumConverters {
    @TypeConverter
    fun fromTransactionType(type: TransactionType): String {
        return type.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}

