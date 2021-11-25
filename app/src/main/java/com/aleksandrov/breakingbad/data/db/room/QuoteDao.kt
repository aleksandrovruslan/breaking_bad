package com.aleksandrov.breakingbad.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quotes: List<QuoteEntity>)

    @Query("SELECT * FROM quotes")
    fun getQuotes(): List<QuoteEntity>

}