package com.aleksandrov.breakingbad.data.repositories

import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.network.BreakingBadApi
import com.aleksandrov.breakingbad.domain.converters.BBConverter
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class BBRepositoryImpl @Inject constructor(
    private val bbApi: BreakingBadApi,
    private val bbStore: BBStore,
    private val converter: BBConverter
) : BBRepository {

    override fun getDeathCount(): DeathCount? = converter.convert(bbStore.getDeathCount()) ?: getRemoteDeathCount()

    override fun getRemoteDeathCount(): DeathCount? =
        bbApi.deathCount()?.also { bbStore.saveDeathCount(converter.reverse(it)) }

    override fun getCharacters(): List<Character>? =
        converter.convertCharactersList(bbStore.getCharacters()) ?: getRemoteCharacters()

    override fun getRemoteCharacters(): List<Character>? =
        bbApi.characters()?.toList()?.also { bbStore.saveCharacters(converter.reverseCharactersList(it)) }

    override fun getCharacterById(id: Int): Character? =
        converter.convert(bbStore.getCharacterById(id)) ?: getRemoteCharacterById(id)

    override fun getRemoteCharacterById(id: Int): Character? = bbApi.characterById(id)

    override fun getRandomCharacter(): Character? =
        converter.convert(bbStore.getRandomCharacter()) ?: getRemoteRandomCharacter()

    override fun getRemoteRandomCharacter(): Character? = bbApi.randomCharacter()

    override fun findCharacterByName(name: String): List<Character>? =
        converter.convertCharactersList(bbStore.findCharacterByName(name)) ?: findRemoteCharacterByName(name)

    override fun findRemoteCharacterByName(name: String): List<Character>? =
        bbApi.findCharacterByName(name)?.toList()

    override fun getEpisodes(): List<Episode>? = converter.convertEpisodesList(bbStore.getEpisodes()) ?: getRemoteEpisodes()

    override fun getRemoteEpisodes(): List<Episode>? =
        bbApi.getEpisodes()?.toList()?.also { bbStore.saveEpisodes(converter.reverseEpisodesList(it)) }

    override fun getEpisodeById(id: Int): Episode? =
        converter.convert(bbStore.getEpisodeById(id)) ?: getRemoteEpisodeById(id)

    override fun getRemoteEpisodeById(id: Int): Episode? =
        bbApi.getEpisodeById(id)?.toList()?.first()

    override fun getQuotes(): List<Quote>? = converter.convertQuotesList(bbStore.getQuotes()) ?: getRemoteQuotes()

    override fun getRemoteQuotes(): List<Quote>? =
        bbApi.getQuotes()?.toList()?.also { bbStore.saveQuotes(converter.reverseQuotesList(it)) }

}