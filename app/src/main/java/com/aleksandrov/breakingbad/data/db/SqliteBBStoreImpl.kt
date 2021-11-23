package com.aleksandrov.breakingbad.data.db

import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity
import javax.inject.Inject

class SqliteBBStoreImpl @Inject constructor(private val helper: BBDbHelper) : BBStore {

    override fun saveDeathCount(deathCount: DeathCountEntity) = helper.addDeathCount(deathCount)

    override fun getDeathCount(): DeathCountEntity? = helper.getDeathCount()

    override fun saveCharacters(characters: List<CharacterEntity>) =
        helper.saveCharacters(characters)

    override fun getCharacters(): List<CharacterEntity>? = helper.getCharacters()

    override fun getCharacterById(id: Int): CharacterEntity? = helper.getCharacterById(id)

    override fun getRandomCharacter(): CharacterEntity? = helper.getRandomCharacter()

    override fun findCharacterByName(name: String): List<CharacterEntity>? =
        helper.findCharacterByName(name)

    override fun getEpisodes(): List<EpisodeEntity>? = helper.getEpisodes()

    override fun saveEpisodes(episodes: List<EpisodeEntity>) = helper.saveEpisodes(episodes)

    override fun getEpisodeById(id: Int): EpisodeEntity? = helper.getEpisodeById(id)

    override fun getQuotes(): List<QuoteEntity>? = helper.getQuotes()

    override fun saveQuotes(quotes: List<QuoteEntity>) = helper.addQuotes(quotes)

}