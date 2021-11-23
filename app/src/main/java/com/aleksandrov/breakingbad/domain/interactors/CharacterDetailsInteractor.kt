package com.aleksandrov.breakingbad.domain.interactors

import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class CharacterDetailsInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить персонажа по id
     * @param id - id персонажа
     * @return персонаж
     */
    fun loadCharacterById(id: Int): Character? = repository.getCharacterById(id)

}