package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Character
import com.aleksandrov.breakingbad.models.DeathCount
import com.aleksandrov.breakingbad.models.Episode
import com.aleksandrov.breakingbad.models.Quote

/**
 * Репозиторий для работы с данными
 */
interface BBRepository {

    /**
     * Получить количество смертей из базы данных
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
     * Получить список список персонажей
     * @return персонажи
     */
    fun getCharacters(): Array<Character>?

    /**
     * Получить персонажа по id
     * @param id - id персонажа
     * @return персонаж
     */
    fun characterById(id: Int): Character?

    /**
     * Получить случайного персонажа
     * @return персонаж
     */
    fun randomCharacter(): Character?

    /**
     * Получить список персонажа по имени
     * @param name - имя персонажа
     * @return список персонажей
     */
    fun findCharacterByName(name: String): Array<Character>?

    /**
     * Получить список эпизодов
     * @return список эпизодов
     */
    fun getEpisodes(): Array<Episode>?

    /**
     * Получить эпизод по id
     * @param id - id эпизода
     * @return эпизод
     */
    fun getEpisodeById(id: Int): Array<Episode>?

    /**
     * Получить список цитат
     * @return список цитат
     */
    fun getQuotes(): Array<Quote>?

}