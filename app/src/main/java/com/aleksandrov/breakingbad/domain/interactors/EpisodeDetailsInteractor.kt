package com.aleksandrov.breakingbad.domain.interactors

import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class EpisodeDetailsInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить эпизод по id
     * @param id - id эпизода
     * @return эпизод
     */
    fun loadEpisodeById(id: Int): Episode? = repository.getEpisodeById(id)

}