package com.aleksandrov.breakingbad.domain.converters

import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote

interface BBConverter {

    fun convert(character: CharacterEntity?): Character?

    fun reverse(character: Character?): CharacterEntity?

    fun convertCharactersList(list: List<CharacterEntity>?): List<Character>?

    fun reverseCharactersList(list: List<Character>): List<CharacterEntity>

    fun convert(count: DeathCountEntity?): DeathCount?

    fun reverse(count: DeathCount): DeathCountEntity

    fun convert(episode: EpisodeEntity?): Episode?

    fun reverse(episode: Episode?): EpisodeEntity?

    fun convertEpisodesList(list: List<EpisodeEntity>?): List<Episode>?

    fun reverseEpisodesList(list: List<Episode>): List<EpisodeEntity>

    fun convert(quote: QuoteEntity?): Quote?

    fun reverse(quote: Quote?): QuoteEntity?

    fun convertQuotesList(list: List<QuoteEntity>?): List<Quote>?

    fun reverseQuotesList(list: List<Quote>): List<QuoteEntity>

}