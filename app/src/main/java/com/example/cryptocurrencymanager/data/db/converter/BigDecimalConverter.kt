package com.example.cryptocurrencymanager.data.db.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverter {
    @TypeConverter
    fun fromString(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun toString(bigDecimal: BigDecimal?): String? {
        return bigDecimal?.toString()
    }
}
