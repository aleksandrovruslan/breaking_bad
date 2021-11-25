package com.aleksandrov.breakingbad.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity

@Dao
interface DeathCountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(count: DeathCountEntity)

    @Query("SELECT * FROM death_count LIMIT 1")
    fun getDeathCount(): DeathCountEntity?

}