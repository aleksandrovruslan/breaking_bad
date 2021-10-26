package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Episode
import javax.inject.Inject

class EpisodeDetainsInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить эпизод по id
     * @param id - id эпизода
     * @return эпизод
     */
    fun loadEpisodeById(id: Int): Episode? = repository.getEpisodeById(id)

}