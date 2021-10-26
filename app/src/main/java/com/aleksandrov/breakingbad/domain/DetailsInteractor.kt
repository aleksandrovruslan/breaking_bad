package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Character
import javax.inject.Inject

class DetailsInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить персонажа по id
     * @param id - id персонажа
     * @return персонаж
     */
    fun loadCharacterById(id: Int): Character? = repository.getRemoteCharacterById(id)

}