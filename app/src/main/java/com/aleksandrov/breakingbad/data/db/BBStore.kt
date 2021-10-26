package com.aleksandrov.breakingbad.data.db

import com.aleksandrov.breakingbad.models.Character
import com.aleksandrov.breakingbad.models.DeathCount
import com.aleksandrov.breakingbad.models.Episode
import com.aleksandrov.breakingbad.models.Quote

interface BBStore {

    /**
     * Сохранить количество смертей в хранилище
     * @param deathCount - количество смертей
     */
    fun saveDeathCount(deathCount: DeathCount)

    /**
     * Получить количество смертей из хранилища
     * @return количество смертей
     */
    fun getDeathCount(): DeathCount?

    /**
     * Сохранить персонажей в хранилище
     * @param characters - персонажи
     */
    fun saveCharacters(characters: List<Character>)

    /**
     * Получить список список персонажей из хранилища
     * @return персонажи
     */
    fun getCharacters(): List<Character>?

    /**
     * Получить персонажа по id из хранилища
     * @param id - id персонажа
     * @return персонаж
     */
    fun getCharacterById(id: Int): Character?

    /**
     * Получить случайного персонажа из хранилища
     * @return персонаж
     */
    fun getRandomCharacter(): Character?

    /**
     * Получить список персонажа по имени из хранилища
     * @param name - имя персонажа
     * @return список персонажей
     */
    fun findCharacterByName(name: String): List<Character>?

    /**
     * Получить список эпизодов из хранилища
     * @return список эпизодов
     */
    fun getEpisodes(): List<Episode>?

    /**
     * Сохранить список эпизодов в хранилище
     * @param episodes - список эпизодов
     */
    fun saveEpisodes(episodes: List<Episode>)

    /**
     * Получить эпизод по id из хранилища
     * @param id - id эпизода
     * @return эпизод
     */
    fun getEpisodeById(id: Int): Episode?

    /**
     * Получить список цитат из хранилища
     * @return список цитат
     */
    fun getQuotes(): List<Quote>?

    /**
     * Сохранить список цитат в хранилище
     * @param quotes - список цитат
     */
    fun saveQuotes(quotes: List<Quote>)

}