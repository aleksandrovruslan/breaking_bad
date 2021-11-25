package com.aleksandrov.breakingbad.domain.interactors

import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class DeathsInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить количество смертей из хранилища
     * @return количество смертей
     */
    fun getDeathCount(): DeathCount? = repository.getDeathCount()

    /**
     * Получить количество смертей с сервера
     * @return количество смертей
     */
    fun getRemoteDeathCount(): DeathCount? = repository.getRemoteDeathCount()

}