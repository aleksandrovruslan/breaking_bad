package com.aleksandrov.breakingbad.data.db

import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity
import com.aleksandrov.breakingbad.data.db.room.CharacterDao
import com.aleksandrov.breakingbad.data.db.room.DeathCountDao
import com.aleksandrov.breakingbad.data.db.room.EpisodeDao
import com.aleksandrov.breakingbad.data.db.room.QuoteDao
import javax.inject.Inject

class RoomBBStoreImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val deathCountDao: DeathCountDao,
    private val episodeDao: EpisodeDao,
    private val quoteDao: QuoteDao,
) : BBStore {

    override fun saveDeathCount(deathCount: DeathCountEntity) = deathCountDao.insert(deathCount)

    override fun getDeathCount(): DeathCountEntity? = deathCountDao.getDeathCount()

    override fun saveCharacters(characters: List<CharacterEntity>) =
        characterDao.insertCharacters(characters)

    override fun getCharacters(): List<CharacterEntity> = characterDao.getCharacters()

    override fun getCharacterById(id: Int): CharacterEntity? = characterDao.getCharacterById(id)

    override fun getRandomCharacter(): CharacterEntity = characterDao.getCharacters().random()

    override fun findCharacterByName(name: String): List<CharacterEntity>? =
        characterDao.findCharacterByName(name)?.let { listOf(it) }

    override fun getEpisodes(): List<EpisodeEntity> = episodeDao.getEpisodes()

    override fun saveEpisodes(episodes: List<EpisodeEntity>) = episodeDao.insert(episodes)

    override fun getEpisodeById(id: Int): EpisodeEntity? = episodeDao.getEpisodeById(id)

    override fun getQuotes(): List<QuoteEntity> = quoteDao.getQuotes()

    override fun saveQuotes(quotes: List<QuoteEntity>) = quoteDao.insert(quotes)

}