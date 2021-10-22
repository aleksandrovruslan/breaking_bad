package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.DeathCount
import javax.inject.Inject

class BBInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить количество смертей
     * @return количество смертей
     */
    fun getDeathCount(): DeathCount? = repository.getDeathCount()

}