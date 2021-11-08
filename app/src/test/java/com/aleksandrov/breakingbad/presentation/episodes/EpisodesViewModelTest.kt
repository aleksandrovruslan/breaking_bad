package com.aleksandrov.breakingbad.presentation.episodes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleksandrov.breakingbad.domain.EpisodesInteractor
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import com.aleksandrov.breakingbad.utils.TestSchedulersProviderImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpisodesViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: EpisodesInteractor

    @Mock
    private lateinit var episodesObserver: Observer<List<Episode>>

    @Mock
    private lateinit var progressObserver: Observer<Event<Boolean>>

    @Mock
    private lateinit var errorObserver: Observer<Event<String>>

    @Mock
    private lateinit var episodes: List<Episode>
    private lateinit var schedulers: SchedulersProvider
    private lateinit var viewModel: EpisodesViewModel
    private val IS_START_PROGRESS = true
    private val IS_STOP_PROGRESS = false
    private val ERROR_MESSAGE = "My exception"

    @Before
    fun setUp() {
        schedulers = TestSchedulersProviderImpl()
        viewModel = EpisodesViewModel(interactor, schedulers)

        viewModel.episodes.observeForever(episodesObserver)
        viewModel.progress.observeForever(progressObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun testLoadEpisodesRemote() {
        `when`(interactor.loadRemoteEpisodes()).thenReturn(episodes)
        val inOrder = Mockito.inOrder(interactor, episodesObserver, progressObserver)

        viewModel.loadEpisodes(true)

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadRemoteEpisodes()
        inOrder.verify(episodesObserver).onChanged(episodes)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testError() {
        `when`(interactor.loadEpisodes()).then { throw IllegalAccessException(ERROR_MESSAGE) }
        val inOrder = Mockito.inOrder(interactor, progressObserver, errorObserver)

        viewModel.loadEpisodes()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadEpisodes()
        inOrder.verify(errorObserver).onChanged(Event(ERROR_MESSAGE))
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testLoadEpisodes() {
        `when`(interactor.loadEpisodes()).thenReturn(episodes)
        val inOrder = Mockito.inOrder(interactor, episodesObserver, progressObserver)

        viewModel.loadEpisodes()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadEpisodes()
        inOrder.verify(episodesObserver).onChanged(episodes)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

}