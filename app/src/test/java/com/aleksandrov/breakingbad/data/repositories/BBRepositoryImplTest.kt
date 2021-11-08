package com.aleksandrov.breakingbad.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.network.BreakingBadApi
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BBRepositoryImplTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bbApi: BreakingBadApi

    @Mock
    private lateinit var bbStore: BBStore

    @Mock
    private lateinit var deathCount: DeathCount

    @Mock
    private lateinit var characters: List<Character>

    @Mock
    private lateinit var character: Character

    @Mock
    private lateinit var episodes: List<Episode>

    @Mock
    private lateinit var episode: Episode

    @Mock
    private lateinit var quotes: List<Quote>
    @Mock
    private lateinit var quote: Quote
    private lateinit var charactersArray: Array<Character>
    private lateinit var episodesArray: Array<Episode>
    private lateinit var quotesArray: Array<Quote>
    private lateinit var repository: BBRepositoryImpl

    @Before
    fun setUp() {
        repository = BBRepositoryImpl(bbApi, bbStore)
    }

    @Test
    fun testGetDeathCount() {
        `when`(bbStore.getDeathCount()).thenReturn(deathCount)
        val inOrder = Mockito.inOrder(bbApi, bbStore)

        val actualCount = repository.getDeathCount()

        assertEquals(deathCount, actualCount)
        inOrder.verify(bbStore).getDeathCount()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testGetDeathCountWithoutStore() {
        `when`(bbStore.getDeathCount()).thenReturn(null)
        `when`(bbApi.deathCount()).thenReturn(deathCount)
        val inOrder = Mockito.inOrder(bbApi, bbStore)

        val actualCount = repository.getDeathCount()

        assertEquals(deathCount, actualCount)
        inOrder.verify(bbStore).getDeathCount()
        inOrder.verify(bbApi).deathCount()
        inOrder.verify(bbStore).saveDeathCount(deathCount)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteDeathCount() {
        `when`(bbApi.deathCount()).thenReturn(deathCount)
        val inOrder = Mockito.inOrder(bbApi, bbStore)

        val actualCount = repository.getRemoteDeathCount()

        assertEquals(deathCount, actualCount)
        inOrder.verify(bbApi).deathCount()
        inOrder.verify(bbStore).saveDeathCount(deathCount)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getCharacters() {
        `when`(bbStore.getCharacters()).thenReturn(characters)
        val inOrder = Mockito.inOrder(bbStore)

        val actualCharacters = repository.getCharacters()

        assertEquals(characters, actualCharacters)
        inOrder.verify(bbStore).getCharacters()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getCharactersWithoutStore() {
        charactersArray = arrayOf(character)
        characters = listOf(character)
        `when`(bbStore.getCharacters()).thenReturn(null)
        `when`(bbApi.characters()).thenReturn(charactersArray)
        val inOrder = Mockito.inOrder(bbStore, bbApi)

        val actualCharacters = repository.getCharacters()

        assertEquals(characters, actualCharacters)
        inOrder.verify(bbStore).getCharacters()
        inOrder.verify(bbApi).characters()
        inOrder.verify(bbStore).saveCharacters(characters)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteCharacters() {
        charactersArray = arrayOf(character)
        characters = listOf(character)
        `when`(bbApi.characters()).thenReturn(charactersArray)
        val inOrder = Mockito.inOrder(bbApi, bbStore)

        val actualCharacters = repository.getRemoteCharacters()

        assertEquals(characters, actualCharacters)
        inOrder.verify(bbApi).characters()
        inOrder.verify(bbStore).saveCharacters(characters)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getCharacterById() {
        `when`(bbStore.getCharacterById(anyInt())).thenReturn(character)
        val inOrder = Mockito.inOrder(bbApi, bbStore)

        val actualCharacter = repository.getCharacterById(anyInt())

        assertEquals(character, actualCharacter)
        inOrder.verify(bbStore).getCharacterById(anyInt())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getCharacterByIdWithoutStore() {
        `when`(bbStore.getCharacterById(anyInt())).thenReturn(null)
        `when`(bbApi.characterById(anyInt())).thenReturn(character)
        val inOrder = Mockito.inOrder(bbApi, bbStore)

        val actualCharacter = repository.getCharacterById(anyInt())

        assertEquals(character, actualCharacter)
        inOrder.verify(bbStore).getCharacterById(anyInt())
        inOrder.verify(bbApi).characterById(anyInt())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteCharacterById() {
        `when`(bbApi.characterById(anyInt())).thenReturn(character)
        val inOrder = Mockito.inOrder(bbApi)

        val actualCharacter = repository.getRemoteCharacterById(anyInt())

        assertEquals(character, actualCharacter)
        inOrder.verify(bbApi).characterById(anyInt())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRandomCharacter() {
        `when`(bbStore.getRandomCharacter()).thenReturn(character)
        val inOrder = Mockito.inOrder(bbStore)

        val actualCharacter = repository.getRandomCharacter()

        assertEquals(character, actualCharacter)
        inOrder.verify(bbStore).getRandomCharacter()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRandomCharacterWithoutStore() {
        `when`(bbStore.getRandomCharacter()).thenReturn(null)
        `when`(bbApi.randomCharacter()).thenReturn(character)
        val inOrder = Mockito.inOrder(bbStore, bbApi)

        val actualCharacter = repository.getRandomCharacter()

        assertEquals(character, actualCharacter)
        inOrder.verify(bbStore).getRandomCharacter()
        inOrder.verify(bbApi).randomCharacter()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteRandomCharacter() {
        `when`(bbApi.randomCharacter()).thenReturn(character)
        val inOrder = Mockito.inOrder(bbApi)

        val actualCharacter = repository.getRemoteRandomCharacter()

        assertEquals(character, actualCharacter)
        inOrder.verify(bbApi).randomCharacter()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun findCharacterByName() {
        `when`(bbStore.findCharacterByName(anyString())).thenReturn(characters)
        val inOrder = Mockito.inOrder(bbStore)

        val actualCharacter = repository.findCharacterByName(anyString())

        assertEquals(characters, actualCharacter)
        inOrder.verify(bbStore).findCharacterByName(anyString())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun findCharacterByNameWithoutStore() {
        charactersArray = arrayOf(character)
        characters = listOf(character)
        `when`(bbStore.findCharacterByName(anyString())).thenReturn(null)
        `when`(bbApi.findCharacterByName(anyString())).thenReturn(charactersArray)
        val inOrder = Mockito.inOrder(bbStore, bbApi)

        val actualCharacter = repository.findCharacterByName(anyString())

        assertEquals(characters, actualCharacter)
        inOrder.verify(bbStore).findCharacterByName(anyString())
        inOrder.verify(bbApi).findCharacterByName(anyString())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun findRemoteCharacterByName() {
        charactersArray = arrayOf(character)
        characters = listOf(character)
        `when`(bbApi.findCharacterByName(anyString())).thenReturn(charactersArray)
        val inOrder = Mockito.inOrder(bbApi)

        val actualCharacter = repository.findRemoteCharacterByName(anyString())

        assertEquals(characters, actualCharacter)
        inOrder.verify(bbApi).findCharacterByName(anyString())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getEpisodes() {
        `when`(bbStore.getEpisodes()).thenReturn(episodes)
        val inOrder = Mockito.inOrder(bbStore)

        val actualEpisodes = repository.getEpisodes()

        assertEquals(episodes, actualEpisodes)
        inOrder.verify(bbStore).getEpisodes()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getEpisodesWithoutStore() {
        episodesArray = arrayOf(episode)
        episodes = listOf(episode)
        `when`(bbStore.getEpisodes()).thenReturn(null)
        `when`(bbApi.getEpisodes()).thenReturn(episodesArray)
        val inOrder = Mockito.inOrder(bbStore, bbApi)

        val actualEpisodes = repository.getEpisodes()

        assertEquals(episodes, actualEpisodes)
        inOrder.verify(bbStore).getEpisodes()
        inOrder.verify(bbApi).getEpisodes()
        inOrder.verify(bbStore).saveEpisodes(episodes)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteEpisodes() {
        episodesArray = arrayOf(episode)
        episodes = listOf(episode)
        `when`(bbApi.getEpisodes()).thenReturn(episodesArray)
        val inOrder = Mockito.inOrder(bbStore, bbApi)

        val actualEpisodes = repository.getRemoteEpisodes()

        assertEquals(episodes, actualEpisodes)
        inOrder.verify(bbApi).getEpisodes()
        inOrder.verify(bbStore).saveEpisodes(episodes)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getEpisodeById() {
        `when`(bbStore.getEpisodeById(anyInt())).thenReturn(episode)
        val inOrder = Mockito.inOrder(bbStore)

        val actualEpisode = repository.getEpisodeById(anyInt())

        assertEquals(episode, actualEpisode)
        inOrder.verify(bbStore).getEpisodeById(anyInt())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getEpisodeByIdWithoutStore() {
        episodesArray = arrayOf(episode)
        `when`(bbStore.getEpisodeById(anyInt())).thenReturn(null)
        `when`(bbApi.getEpisodeById(anyInt())).thenReturn(episodesArray)
        val inOrder = Mockito.inOrder(bbStore, bbApi)

        val actualEpisode = repository.getEpisodeById(anyInt())

        assertEquals(episode, actualEpisode)
        inOrder.verify(bbStore).getEpisodeById(anyInt())
        inOrder.verify(bbApi).getEpisodeById(anyInt())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteEpisodeById() {
        episodesArray = arrayOf(episode)
        `when`(bbApi.getEpisodeById(anyInt())).thenReturn(episodesArray)
        val inOrder = Mockito.inOrder(bbApi)

        val actualEpisode = repository.getRemoteEpisodeById(anyInt())

        assertEquals(episode, actualEpisode)
        inOrder.verify(bbApi).getEpisodeById(anyInt())
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getQuotes() {
        `when`(bbStore.getQuotes()).thenReturn(quotes)
        val inOrder = Mockito.inOrder(bbStore)

        val actualQuotes = repository.getQuotes()

        assertEquals(quotes, actualQuotes)
        inOrder.verify(bbStore).getQuotes()
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getQuotesWithoutStore() {
        quotesArray = arrayOf(quote)
        quotes = listOf(quote)
        `when`(bbStore.getQuotes()).thenReturn(null)
        `when`(bbApi.getQuotes()).thenReturn(quotesArray)
        val inOrder = Mockito.inOrder(bbStore,bbApi)

        val actualQuotes = repository.getQuotes()

        assertEquals(quotes, actualQuotes)
        inOrder.verify(bbStore).getQuotes()
        inOrder.verify(bbApi).getQuotes()
        inOrder.verify(bbStore).saveQuotes(quotes)
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getRemoteQuotes() {
        quotesArray = arrayOf(quote)
        quotes = listOf(quote)
        `when`(bbApi.getQuotes()).thenReturn(quotesArray)
        val inOrder = Mockito.inOrder(bbStore,bbApi)

        val actualQuotes = repository.getRemoteQuotes()

        assertEquals(quotes, actualQuotes)
        inOrder.verify(bbApi).getQuotes()
        inOrder.verify(bbStore).saveQuotes(quotes)
        inOrder.verifyNoMoreInteractions()
    }

}