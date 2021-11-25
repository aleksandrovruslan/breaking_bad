package com.aleksandrov.breakingbad.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.domain.interactors.CharactersInteractor
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersInteractorTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: BBRepository

    @Mock
    private lateinit var characters: List<Character>
    private lateinit var interactor: CharactersInteractor

    @Before
    fun setUp() {
        interactor = CharactersInteractor(repository)
    }

    @Test
    fun testCharacters() {
        `when`(repository.getCharacters()).thenReturn(characters)

        val actualCharacters = interactor.getCharacters()

        assertEquals(characters, actualCharacters)
        verify(repository).getCharacters()
    }

    @Test
    fun testRemoteCharacters() {
        `when`(repository.getRemoteCharacters()).thenReturn(characters)

        val actualCharacters = interactor.getRemoteCharacters()

        assertEquals(characters, actualCharacters)
        verify(repository).getRemoteCharacters()
    }

}