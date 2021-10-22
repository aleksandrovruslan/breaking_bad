package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Character
import javax.inject.Inject

class CharactersInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить список список персонажей
     * @return персонажи
     */
    fun getCharacters(): Array<Character>? = repository.getCharacters()

}