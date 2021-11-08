package com.aleksandrov.breakingbad.data.db

import android.content.SharedPreferences
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote
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

    override fun saveDeathCount(deathCount: DeathCount) {
        val adapter: JsonAdapter<DeathCount> = moshi.adapter(DeathCount::class.java)
        sharedPreferences.edit().putString(DEATH_COUNT, adapter.toJson(deathCount)).commit()
    }

    override fun getDeathCount(): DeathCount? {
        val deathCountString = sharedPreferences.getString(DEATH_COUNT, null)
        val adapter: JsonAdapter<DeathCount> = moshi.adapter(DeathCount::class.java)
        return deathCountString?.let(adapter::fromJson)
    }

    override fun saveCharacters(characters: List<Character>) {
        val adapter: JsonAdapter<Array<Character>> = moshi.adapter(Array<Character>::class.java)
        sharedPreferences.edit().putString(CHARACTERS, adapter.toJson(characters.toTypedArray()))
            .commit()
    }

    override fun getCharacters(): List<Character>? {
        val characters = sharedPreferences.getString(CHARACTERS, null)
        val adapter: JsonAdapter<Array<Character>> = moshi.adapter(Array<Character>::class.java)
        return characters?.let(adapter::fromJson)?.toList()
    }

    override fun getCharacterById(id: Int): Character? = getCharacters()?.find { it.char_id == id }

    override fun getRandomCharacter(): Character? = getCharacters()?.random()

    override fun findCharacterByName(name: String): List<Character>? =
        getCharacters()?.filter { it.name.equals(name, true) }

    override fun getEpisodes(): List<Episode>? {
        val episodes = sharedPreferences.getString(EPISODES, null)
        val adapter: JsonAdapter<Array<Episode>> = moshi.adapter(Array<Episode>::class.java)
        return episodes?.let(adapter::fromJson)?.toList()
    }

    override fun saveEpisodes(episodes: List<Episode>) {
        val adapter: JsonAdapter<Array<Episode>> = moshi.adapter(Array<Episode>::class.java)
        sharedPreferences.edit().putString(EPISODES, adapter.toJson(episodes.toTypedArray()))
            .commit()
    }

    override fun getEpisodeById(id: Int): Episode? = getEpisodes()?.find { it.episode_id == id }

    override fun getQuotes(): List<Quote>? {
        val quotes = sharedPreferences.getString(QUOTES, null)
        val adapter: JsonAdapter<Array<Quote>> = moshi.adapter(Array<Quote>::class.java)
        return quotes?.let(adapter::fromJson)?.toList()
    }

    override fun saveQuotes(quotes: List<Quote>) {
        val adapter: JsonAdapter<Array<Quote>> = moshi.adapter(Array<Quote>::class.java)
        sharedPreferences.edit().putString(QUOTES, adapter.toJson(quotes.toTypedArray())).commit()
    }

}