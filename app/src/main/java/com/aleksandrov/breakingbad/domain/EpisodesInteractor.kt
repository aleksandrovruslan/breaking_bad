package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Episode
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