package com.aleksandrov.breakingbad.data.db

import android.content.SharedPreferences
import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

const val DEATH_COUNT = "DEATH_COUNT"
const val CHARACTERS = "CHARACTERS"
const val EPISODES = "EPISODES"
const val QUOTES = "QUOTES"

class PrefsBBStoreImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi,
) : BBStore {

    override fun saveDeathCount(deathCount: DeathCountEntity) {
        val adapter: JsonAdapter<DeathCountEntity> = moshi.adapter(DeathCountEntity::class.java)
        sharedPreferences.edit().putString(DEATH_COUNT, adapter.toJson(deathCount)).apply()
    }

    override fun getDeathCount(): DeathCountEntity? {
        val deathCountString = sharedPreferences.getString(DEATH_COUNT, null)
        val adapter: JsonAdapter<DeathCountEntity> = moshi.adapter(DeathCountEntity::class.java)
        return deathCountString?.let(adapter::fromJson)
    }

    override fun saveCharacters(characters: List<CharacterEntity>) {
        val adapter: JsonAdapter<Array<CharacterEntity>> =
            moshi.adapter(Array<CharacterEntity>::class.java)
        sharedPreferences.edit().putString(CHARACTERS, adapter.toJson(characters.toTypedArray()))
            .apply()
    }

    override fun getCharacters(): List<CharacterEntity>? {
        val characters = sharedPreferences.getString(CHARACTERS, null)
        val adapter: JsonAdapter<Array<CharacterEntity>> =
            moshi.adapter(Array<CharacterEntity>::class.java)
        return characters?.let(adapter::fromJson)?.toList()
    }

    override fun getCharacterById(id: Int): CharacterEntity? =
        getCharacters()?.find { it.char_id == id }

    override fun getRandomCharacter(): CharacterEntity? = getCharacters()?.random()

    override fun findCharacterByName(name: String): List<CharacterEntity>? =
        getCharacters()?.filter { it.name.equals(name, true) }

    override fun getEpisodes(): List<EpisodeEntity>? {
        val episodes = sharedPreferences.getString(EPISODES, null)
        val adapter: JsonAdapter<Array<EpisodeEntity>> =
            moshi.adapter(Array<EpisodeEntity>::class.java)
        return episodes?.let(adapter::fromJson)?.toList()
    }

    override fun saveEpisodes(episodes: List<EpisodeEntity>) {
        val adapter: JsonAdapter<Array<EpisodeEntity>> =
            moshi.adapter(Array<EpisodeEntity>::class.java)
        sharedPreferences.edit().putString(EPISODES, adapter.toJson(episodes.toTypedArray()))
            .apply()
    }

    override fun getEpisodeById(id: Int): EpisodeEntity? =
        getEpisodes()?.find { it.episode_id == id }

    override fun getQuotes(): List<QuoteEntity>? {
        val quotes = sharedPreferences.getString(QUOTES, null)
        val adapter: JsonAdapter<Array<QuoteEntity>> = moshi.adapter(Array<QuoteEntity>::class.java)
        return quotes?.let(adapter::fromJson)?.toList()
    }

    override fun saveQuotes(quotes: List<QuoteEntity>) {
        val adapter: JsonAdapter<Array<QuoteEntity>> = moshi.adapter(Array<QuoteEntity>::class.java)
        sharedPreferences.edit().putString(QUOTES, adapter.toJson(quotes.toTypedArray())).apply()
    }

}