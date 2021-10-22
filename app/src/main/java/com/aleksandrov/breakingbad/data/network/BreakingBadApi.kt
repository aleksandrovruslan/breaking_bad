package com.aleksandrov.breakingbad.data.network

import com.aleksandrov.breakingbad.models.Character
import com.aleksandrov.breakingbad.models.DeathCount

interface BreakingBadApi {

    /**
     * Получить общее количество смертей
     * @return общее количество смертей
     */
    fun deathCount(): DeathCount?

    /**
     * Получить всех персонажей сериала
     * @return список персонажей
     */
    fun characters(): Array<Character>?

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

}