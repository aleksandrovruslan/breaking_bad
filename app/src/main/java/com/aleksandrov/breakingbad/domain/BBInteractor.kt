package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.DeathCount
import javax.inject.Inject

class BBInteractor @Inject constructor(private var repository: BBRepository) {

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