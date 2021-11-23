package com.aleksandrov.breakingbad.domain.interactors

import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class CharactersInteractor @Inject constructor(private var repository: BBRepository) {

    /**
     * Получить список список персонажей из хранилища
     * @return персонажи
     */
    fun getCharacters(): List<Character>? = repository.getCharacters()

    /**
     * Получить список список персонажей с сервера
     * @return персонажи
     */
    fun getRemoteCharacters(): List<Character>? = repository.getRemoteCharacters()

}