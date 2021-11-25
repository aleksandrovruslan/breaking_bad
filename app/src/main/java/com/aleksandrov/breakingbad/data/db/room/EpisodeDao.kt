package com.aleksandrov.breakingbad.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episodes: List<EpisodeEntity>)

    @Query("SELECT * FROM episodes")
    fun getEpisodes(): List<EpisodeEntity>

    @Query("SELECT * FROM episodes WHERE episode_id = :id")
    fun getEpisodeById(id: Int): EpisodeEntity?

}