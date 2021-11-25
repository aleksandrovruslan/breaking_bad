package com.aleksandrov.breakingbad.domain.interactors

import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class EpisodesInteractor @Inject constructor(private val repository: BBRepository) {

    /**
     * Получить список эпизодов из хранилища
     * @return список эпизодов
     */
    fun loadEpisodes(): List<Episode>? = repository.getEpisodes()

    /**
     * Получить список эпизодов с сервера
     * @return список эпизодов
     */
    fun loadRemoteEpisodes(): List<Episode>? = repository.getRemoteEpisodes()

}