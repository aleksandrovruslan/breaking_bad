package com.aleksandrov.breakingbad.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.domain.interactors.EpisodeDetailsInteractor
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpisodeDetailsInteractorTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: BBRepository

    @Mock
    private lateinit var episode: Episode
    private lateinit var interactor: EpisodeDetailsInteractor

    @Before
    fun setUp() {
        interactor = EpisodeDetailsInteractor(repository)
    }

    @Test
    fun loadEpisodeById() {
        `when`(repository.getEpisodeById(anyInt())).thenReturn(episode)

        val actualEpisode = interactor.loadEpisodeById(anyInt())

        assertEquals(episode, actualEpisode)
        verify(repository).getEpisodeById(anyInt())
    }

}