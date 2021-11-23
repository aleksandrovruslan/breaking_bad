package com.aleksandrov.breakingbad.data.db.entity

data class EpisodeEntity(
    val episode_id: Int,
    val title: String,
    val season: String,
    val air_date: String,
    val characters: List<String>,
    val episode: String,
    val series: String,
)
