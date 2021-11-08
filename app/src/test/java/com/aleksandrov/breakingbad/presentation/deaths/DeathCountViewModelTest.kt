package com.aleksandrov.breakingbad.presentation.deaths

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleksandrov.breakingbad.domain.DeathsInteractor
import com.aleksandrov.breakingbad.domain.models.DeathCount
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
class DeathCountViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: DeathsInteractor

    @Mock
    private lateinit var deathCountObserver: Observer<Int>

    @Mock
    private lateinit var errorObserver: Observer<Event<String>>

    @Mock
    private lateinit var progressObserver: Observer<Event<Boolean>>

    private lateinit var schedulers: SchedulersProvider

    private lateinit var viewModel: DeathCountViewModel
    private val IS_START_PROGRESS = true
    private val IS_STOP_PROGRESS = false
    private val ERROR_MESSAGE = "My exception"
    private val CACHED_COUNT = 23
    private val REMOTE_COUNT = 42

    @Before
    fun setUp() {
        schedulers = TestSchedulersProviderImpl()
        viewModel = DeathCountViewModel(interactor, schedulers)

        viewModel.deathCount.observeForever(deathCountObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.progress.observeForever(progressObserver)
    }

    @Test
    fun testDeathCountCached() {
        `when`(interactor.getDeathCount()).thenReturn(DeathCount(CACHED_COUNT))
        val inOrder = Mockito.inOrder(progressObserver, deathCountObserver, interactor)

        viewModel.loadDeathCount()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).getDeathCount()
        inOrder.verify(deathCountObserver).onChanged(CACHED_COUNT)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testDeathCountRemote() {
        `when`(interactor.getRemoteDeathCount()).thenReturn(DeathCount(REMOTE_COUNT))
        val inOrder = Mockito.inOrder(progressObserver, deathCountObserver, interactor)

        viewModel.loadDeathCount(true)

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).getRemoteDeathCount()
        inOrder.verify(deathCountObserver).onChanged(REMOTE_COUNT)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testError() {
        `when`(interactor.getDeathCount()).then { throw IllegalAccessException(ERROR_MESSAGE) }

        val inOrder = Mockito.inOrder(deathCountObserver, progressObserver, errorObserver)

        viewModel.loadDeathCount()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(errorObserver).onChanged(Event(ERROR_MESSAGE))
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

}