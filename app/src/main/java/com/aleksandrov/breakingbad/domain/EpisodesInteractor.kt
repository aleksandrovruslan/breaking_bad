package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Episode
import javax.inject.Inject

class EpisodesInteractor @Inject constructor(private val repository: BBRepository) {

    fun loadEpisodes(): List<Episode>? = repository.getEpisodes()?.toList()

}