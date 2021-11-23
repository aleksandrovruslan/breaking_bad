package com.aleksandrov.breakingbad.presentation.episodedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleksandrov.breakingbad.domain.interactors.EpisodeDetailsInteractor
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import com.aleksandrov.breakingbad.utils.TestSchedulersProviderImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpisodeDetailsViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: EpisodeDetailsInteractor

    @Mock
    private lateinit var episodeObserver: Observer<Episode>

    @Mock
    private lateinit var progressObserver: Observer<Event<Boolean>>

    @Mock
    private lateinit var errorObserver: Observer<Event<String>>

    @Mock
    private lateinit var episode: Episode

    private lateinit var schedulers: SchedulersProvider
    private lateinit var viewModel: EpisodeDetailsViewModel
    private val IS_START_PROGRESS = true
    private val IS_STOP_PROGRESS = false
    private val ERROR_MESSAGE = "My exception"

    @Before
    fun setUp() {
        schedulers = TestSchedulersProviderImpl()
        viewModel = EpisodeDetailsViewModel(interactor, schedulers)

        viewModel.episode.observeForever(episodeObserver)
        viewModel.progress.observeForever(progressObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun testError() {
        `when`(interactor.loadEpisodeById(anyInt())).then { throw IllegalAccessException(ERROR_MESSAGE) }
        val inOrder = Mockito.inOrder(interactor, episodeObserver, progressObserver, errorObserver)

        viewModel.loadEpisodeById(anyInt())

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadEpisodeById(anyInt())
        inOrder.verify(errorObserver).onChanged(Event(ERROR_MESSAGE))
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testLoadEpisodeById() {
        `when`(interactor.loadEpisodeById(anyInt())).thenReturn(episode)
        val inOrder =
            Mockito.inOrder(interactor, episodeObserver, progressObserver, errorObserver)

        viewModel.loadEpisodeById(anyInt())

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadEpisodeById(anyInt())
        inOrder.verify(episodeObserver).onChanged(episode)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

}