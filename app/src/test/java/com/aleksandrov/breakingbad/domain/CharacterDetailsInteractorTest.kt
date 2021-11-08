package com.aleksandrov.breakingbad.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.domain.models.Character
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsInteractorTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: BBRepository

    @Mock
    private lateinit var character: Character
    private lateinit var interactor: CharacterDetailsInteractor

    @Before
    fun setUp() {
        interactor = CharacterDetailsInteractor(repository)
    }

    @Test
    fun loadCharacterById() {
        `when`(repository.getCharacterById(anyInt())).thenReturn(character)

        val actualCharacter = interactor.loadCharacterById(anyInt())

        assertEquals(character, actualCharacter)
        verify(repository).getCharacterById(anyInt())
    }

}