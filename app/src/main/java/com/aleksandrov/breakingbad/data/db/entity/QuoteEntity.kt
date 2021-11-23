package com.aleksandrov.breakingbad.data.db.entity

data class QuoteEntity(
    val quote_id: Int,
    val quote: String,
    val author: String,
    val series: String,
)
