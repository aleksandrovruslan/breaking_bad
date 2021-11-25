package com.aleksandrov.breakingbad.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val episode_id: Int,
    val title: String,
    val season: String,
    val air_date: String,
    val characters: String,
    val episode: String,
    val series: String,
)
