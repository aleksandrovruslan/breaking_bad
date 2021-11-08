package com.aleksandrov.breakingbad.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.domain.models.Episode
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpisodesInteractorTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: BBRepository

    @Mock
    private lateinit var episodes: List<Episode>
    private lateinit var interactor: EpisodesInteractor

    @Before
    fun setUp() {
        interactor = EpisodesInteractor(repository)
    }

    @Test
    fun testLoadEpisodes() {
        `when`(repository.getEpisodes()).thenReturn(episodes)

        val actualEpisodes = interactor.loadEpisodes()

        assertEquals(episodes, actualEpisodes)
        verify(repository).getEpisodes()
    }

    @Test
    fun testLoadRemoteEpisodes() {
        `when`(repository.getRemoteEpisodes()).thenReturn(episodes)

        val actualEpisodes = interactor.loadRemoteEpisodes()

        assertEquals(episodes, actualEpisodes)
        verify(repository).getRemoteEpisodes()
    }

}