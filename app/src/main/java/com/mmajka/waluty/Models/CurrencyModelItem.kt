package com.mmajka.waluty.Models

data class CurrencyModelItem(
    val effectiveDate: String,
    val no: String,
    val rates: List<Rate>,
    val table: String
)