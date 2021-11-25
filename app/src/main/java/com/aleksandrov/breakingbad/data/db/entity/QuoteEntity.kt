package com.aleksandrov.breakingbad.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey val quote_id: Int,
    val quote: String,
    val author: String,
    val series: String,
)
