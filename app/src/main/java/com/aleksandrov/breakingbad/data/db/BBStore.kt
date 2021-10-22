package com.aleksandrov.breakingbad.data.db

import com.aleksandrov.breakingbad.models.DeathCount

interface BBStore {

    /**
     * Сохранить количество смертей в базу
     */
    fun saveDeathCount(deathCount: DeathCount)

    /**
     * Получить количество смертей из базы
     */
    fun getDeathCount(): DeathCount?

}