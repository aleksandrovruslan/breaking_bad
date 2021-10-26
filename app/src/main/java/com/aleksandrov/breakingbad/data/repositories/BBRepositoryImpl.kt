package com.aleksandrov.breakingbad.data.repositories

import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.network.BreakingBadApi
import com.aleksandrov.breakingbad.domain.BBRepository
import com.aleksandrov.breakingbad.models.Character
import com.aleksandrov.breakingbad.models.DeathCount
import com.aleksandrov.breakingbad.models.Episode
import com.aleksandrov.breakingbad.models.Quote
import javax.inject.Inject

class BBRepositoryImpl @Inject constructor(
    private val bbApi: BreakingBadApi,
    private val bbStore: BBStore,
) : BBRepository {

    override fun getDeathCount(): DeathCount? = bbStore.getDeathCount() ?: getRemoteDeathCount()

    override fun getRemoteDeathCount(): DeathCount? = bbApi.deathCount()

    override fun getCharacters(): Array<Character>? = bbApi.characters()

    override fun characterById(id: Int): Character? = bbApi.characterById(id)

    override fun randomCharacter(): Character? = bbApi.randomCharacter()

    override fun findCharacterByName(name: String): Array<Character>? =
        bbApi.findCharacterByName(name)

    override fun getEpisodes(): Array<Episode>? = bbApi.getEpisodes()

    override fun getEpisodeById(id: Int): Array<Episode>? = bbApi.getEpisodeById(id)

    override fun getQuotes(): Array<Quote>? = bbApi.getQuotes()

}