package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote

/**
 * Репозиторий для работы с данными
 */
interface BBRepository {

    /**
     * Получить количество смертей из хранилища
     * , если нет данных, то получаем с сервера
     * @return количество смертей
     */
    fun getDeathCount(): DeathCount?

    /**
     * Получить количество смертей с сервера
     * @return количество смертей
     */
    fun getRemoteDeathCount(): DeathCount?

    /**
     * Получить список список персонажей из хранилища
     * @return персонажи
     */
    fun getCharacters(): List<Character>?

    /**
     * Получить список список персонажей с сервера
     * @return персонажи
     */
    fun getRemoteCharacters(): List<Character>?

    /**
     * Получить персонажа по id из хранилища
     * @param id - id персонажа
     * @return персонаж
     */
    fun getCharacterById(id: Int): Character?

    /**
     * Получить персонажа по id с сервера
     * @param id - id персонажа
     * @return персонаж
     */
    fun getRemoteCharacterById(id: Int): Character?

    /**
     * Получить случайного персонажа из хранилища
     * @return персонаж
     */
    fun getRandomCharacter(): Character?

    /**
     * Получить случайного персонажа с сервера
     * @return персонаж
     */
    fun getRemoteRandomCharacter(): Character?

    /**
     * Получить список персонажа по имени из хранилища
     * @param name - имя персонажа
     * @return список персонажей
     */
    fun findCharacterByName(name: String): List<Character>?

    /**
     * Получить список персонажа по имени с сервера
     * @param name - имя персонажа
     * @return список персонажей
     */
    fun findRemoteCharacterByName(name: String): List<Character>?

    /**
     * Получить список эпизодов из хранилища
     * @return список эпизодов
     */
    fun getEpisodes(): List<Episode>?

    /**
     * Получить список эпизодов с сервера
     * @return список эпизодов
     */
    fun getRemoteEpisodes(): List<Episode>?

    /**
     * Получить эпизод по id из хранилища
     * @param id - id эпизода
     * @return эпизод
     */
    fun getEpisodeById(id: Int): Episode?

    /**
     * Получить эпизод по id с сервера
     * @param id - id эпизода
     * @return эпизод
     */
    fun getRemoteEpisodeById(id: Int): Episode?

    /**
     * Получить список цитат из хранилища
     * @return список цитат
     */
    fun getQuotes(): List<Quote>?

    /**
     * Получить список цитат с сервера
     * @return список цитат
     */
    fun getRemoteQuotes(): List<Quote>?

}