package com.aleksandrov.breakingbad.presentation.quotes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleksandrov.breakingbad.domain.QuotesInteractor
import com.aleksandrov.breakingbad.domain.models.Quote
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
class QuotesViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: QuotesInteractor

    @Mock
    private lateinit var quotesObserver: Observer<List<Quote>>

    @Mock
    private lateinit var progressObserver: Observer<Event<Boolean>>

    @Mock
    private lateinit var errorObserver: Observer<Event<String>>

    @Mock
    private lateinit var quotes: List<Quote>
    private lateinit var schedulers: SchedulersProvider
    private lateinit var viewModel: QuotesViewModel
    private val IS_START_PROGRESS = true
    private val IS_STOP_PROGRESS = false
    private val ERROR_MESSAGE = "My exception"

    @Before
    fun setUp() {
        schedulers = TestSchedulersProviderImpl()
        viewModel = QuotesViewModel(interactor, schedulers)

        viewModel.quotes.observeForever(quotesObserver)
        viewModel.progress.observeForever(progressObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun testLoadQuotesRemote() {
        `when`(interactor.loadRemoteQuotes()).thenReturn(quotes)
        val inOrder = Mockito.inOrder(interactor, quotesObserver, progressObserver)

        viewModel.loadQuotes(true)

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadRemoteQuotes()
        inOrder.verify(quotesObserver).onChanged(quotes)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun getError() {
        `when`(interactor.loadQuotes()).then { throw IllegalStateException(ERROR_MESSAGE) }
        val inOrder = Mockito.inOrder(interactor, quotesObserver, progressObserver, errorObserver)

        viewModel.loadQuotes()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadQuotes()
        inOrder.verify(errorObserver).onChanged(Event(ERROR_MESSAGE))
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testLoadQuotes() {
        `when`(interactor.loadQuotes()).thenReturn(quotes)
        val inOrder = Mockito.inOrder(interactor, quotesObserver, progressObserver)

        viewModel.loadQuotes()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadQuotes()
        inOrder.verify(quotesObserver).onChanged(quotes)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

}