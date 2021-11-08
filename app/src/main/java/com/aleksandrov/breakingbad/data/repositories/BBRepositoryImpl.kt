package com.aleksandrov.breakingbad.data.repositories

import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.network.BreakingBadApi
import com.aleksandrov.breakingbad.domain.BBRepository
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote
import javax.inject.Inject

class BBRepositoryImpl @Inject constructor(
    private val bbApi: BreakingBadApi,
    private val bbStore: BBStore,
) : BBRepository {

    override fun getDeathCount(): DeathCount? = bbStore.getDeathCount() ?: getRemoteDeathCount()

    override fun getRemoteDeathCount(): DeathCount? =
        bbApi.deathCount()?.also { bbStore.saveDeathCount(it) }

    override fun getCharacters(): List<Character>? =
        bbStore.getCharacters() ?: getRemoteCharacters()

    override fun getRemoteCharacters(): List<Character>? =
        bbApi.characters()?.toList()?.also { bbStore.saveCharacters(it) }

    override fun getCharacterById(id: Int): Character? =
        bbStore.getCharacterById(id) ?: getRemoteCharacterById(id)

    override fun getRemoteCharacterById(id: Int): Character? = bbApi.characterById(id)

    override fun getRandomCharacter(): Character? =
        bbStore.getRandomCharacter() ?: getRemoteRandomCharacter()

    override fun getRemoteRandomCharacter(): Character? = bbApi.randomCharacter()

    override fun findCharacterByName(name: String): List<Character>? =
        bbStore.findCharacterByName(name) ?: findRemoteCharacterByName(name)

    override fun findRemoteCharacterByName(name: String): List<Character>? =
        bbApi.findCharacterByName(name)?.toList()

    override fun getEpisodes(): List<Episode>? = bbStore.getEpisodes() ?: getRemoteEpisodes()

    override fun getRemoteEpisodes(): List<Episode>? =
        bbApi.getEpisodes()?.toList()?.also { bbStore.saveEpisodes(it) }

    override fun getEpisodeById(id: Int): Episode? =
        bbStore.getEpisodeById(id) ?: getRemoteEpisodeById(id)

    override fun getRemoteEpisodeById(id: Int): Episode? =
        bbApi.getEpisodeById(id)?.toList()?.first()

    override fun getQuotes(): List<Quote>? = bbStore.getQuotes() ?: getRemoteQuotes()

    override fun getRemoteQuotes(): List<Quote>? =
        bbApi.getQuotes()?.toList()?.also { bbStore.saveQuotes(it) }

}