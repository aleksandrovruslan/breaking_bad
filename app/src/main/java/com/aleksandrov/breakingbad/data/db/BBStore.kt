package com.aleksandrov.breakingbad.data.db

import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity

interface BBStore {

    /**
     * Сохранить количество смертей в хранилище
     * @param deathCount - количество смертей
     */
    fun saveDeathCount(deathCount: DeathCountEntity)

    /**
     * Получить количество смертей из хранилища
     * @return количество смертей
     */
    fun getDeathCount(): DeathCountEntity?

    /**
     * Сохранить персонажей в хранилище
     * @param characters - персонажи
     */
    fun saveCharacters(characters: List<CharacterEntity>)

    /**
     * Получить список список персонажей из хранилища
     * @return персонажи
     */
    fun getCharacters(): List<CharacterEntity>?

    /**
     * Получить персонажа по id из хранилища
     * @param id - id персонажа
     * @return персонаж
     */
    fun getCharacterById(id: Int): CharacterEntity?

    /**
     * Получить случайного персонажа из хранилища
     * @return персонаж
     */
    fun getRandomCharacter(): CharacterEntity?

    /**
     * Получить список персонажа по имени из хранилища
     * @param name - имя персонажа
     * @return список персонажей
     */
    fun findCharacterByName(name: String): List<CharacterEntity>?

    /**
     * Получить список эпизодов из хранилища
     * @return список эпизодов
     */
    fun getEpisodes(): List<EpisodeEntity>?

    /**
     * Сохранить список эпизодов в хранилище
     * @param episodes - список эпизодов
     */
    fun saveEpisodes(episodes: List<EpisodeEntity>)

    /**
     * Получить эпизод по id из хранилища
     * @param id - id эпизода
     * @return эпизод
     */
    fun getEpisodeById(id: Int): EpisodeEntity?

    /**
     * Получить список цитат из хранилища
     * @return список цитат
     */
    fun getQuotes(): List<QuoteEntity>?

    /**
     * Сохранить список цитат в хранилище
     * @param quotes - список цитат
     */
    fun saveQuotes(quotes: List<QuoteEntity>)

}