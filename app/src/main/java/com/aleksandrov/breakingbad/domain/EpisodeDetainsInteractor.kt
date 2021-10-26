package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Episode
import javax.inject.Inject

class EpisodeDetainsInteractor @Inject constructor(private var repository: BBRepository) {

    fun loadEpisodeById(id: Int): Episode? = repository.getEpisodeById(id)?.first()

}