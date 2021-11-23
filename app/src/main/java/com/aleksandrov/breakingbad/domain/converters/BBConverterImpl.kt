package com.aleksandrov.breakingbad.domain.converters

import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote

class BBConverterImpl : BBConverter {

    override fun convert(character: CharacterEntity?): Character? = character?.let {
        Character(
            character.char_id,
            character.name,
            character.birthday,
            character.occupation,
            character.img,
            character.status,
            character.nickname,
            character.appearance,
            character.portrayed,
            character.category,
            character.better_call_saul_appearance
        )
    }

    override fun convert(count: DeathCountEntity?): DeathCount? = count?.let {
        DeathCount(count.deathCount)
    }

    override fun convert(episode: EpisodeEntity?): Episode? = episode?.let {
        Episode(
            episode.episode_id,
            episode.title,
            episode.season,
            episode.air_date,
            episode.characters,
            episode.episode,
            episode.series
        )
    }

    override fun convert(quote: QuoteEntity?): Quote? = quote?.let {
        Quote(quote.quote_id, quote.quote, quote.author, quote.series)
    }

    override fun reverse(character: Character?): CharacterEntity? = character?.let {
        CharacterEntity(
            character.char_id,
            character.name,
            character.birthday,
            character.occupation,
            character.img,
            character.status,
            character.nickname,
            character.appearance,
            character.portrayed,
            character.category,
            character.better_call_saul_appearance
        )
    }

    override fun reverse(count: DeathCount): DeathCountEntity = DeathCountEntity(count.deathCount)

    override fun reverse(episode: Episode?): EpisodeEntity? = episode?.let {
        EpisodeEntity(episode.episode_id,
            episode.title,
            episode.season,
            episode.air_date,
            episode.characters,
            episode.episode,
            episode.series)
    }

    override fun reverse(quote: Quote?): QuoteEntity? = quote?.let {
        QuoteEntity(quote.quote_id, quote.quote, quote.author, quote.series)
    }

    override fun convertCharactersList(list: List<CharacterEntity>?): List<Character>? = list?.let {
        it.map { character ->
            Character(character.char_id,
                character.name,
                character.birthday,
                character.occupation,
                character.img,
                character.status,
                character.nickname,
                character.appearance,
                character.portrayed,
                character.category,
                character.better_call_saul_appearance)
        }
    }

    override fun reverseCharactersList(list: List<Character>): List<CharacterEntity> = list.let {
        it.map { character ->
            CharacterEntity(character.char_id,
                character.name,
                character.birthday,
                character.occupation,
                character.img,
                character.status,
                character.nickname,
                character.appearance,
                character.portrayed,
                character.category,
                character.better_call_saul_appearance)
        }
    }

    override fun convertEpisodesList(list: List<EpisodeEntity>?): List<Episode>? = list?.let {
        it.map { episode ->
            Episode(episode.episode_id,
                episode.title,
                episode.season,
                episode.air_date,
                episode.characters,
                episode.episode,
                episode.series)
        }
    }

    override fun reverseEpisodesList(list: List<Episode>): List<EpisodeEntity> = list.let {
        it.map { episode ->
            EpisodeEntity(episode.episode_id,
                episode.title,
                episode.season,
                episode.air_date,
                episode.characters,
                episode.episode,
                episode.series)
        }
    }

    override fun convertQuotesList(list: List<QuoteEntity>?): List<Quote>? = list?.let {
        it.map { quote -> Quote(quote.quote_id, quote.quote, quote.author, quote.series) }
    }

    override fun reverseQuotesList(list: List<Quote>): List<QuoteEntity> = list.let {
        it.map { quote -> QuoteEntity(quote.quote_id, quote.quote, quote.author, quote.series) }
    }

}