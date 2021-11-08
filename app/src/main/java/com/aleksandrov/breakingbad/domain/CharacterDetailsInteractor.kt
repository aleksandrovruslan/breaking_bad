package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.domain.models.Character
import javax.inject.Inject

class CharacterDetailsInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить персонажа по id
     * @param id - id персонажа
     * @return персонаж
     */
    fun loadCharacterById(id: Int): Character? = repository.getCharacterById(id)

}