package com.aleksandrov.breakingbad.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.domain.models.Quote
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
class QuotesInteractorTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repository: BBRepository
    @Mock
    private lateinit var quotes: List<Quote>
    private lateinit var interactor: QuotesInteractor

    @Before
    fun setUp() {
        interactor = QuotesInteractor(repository)
    }

    @Test
    fun testLoadQuotes() {
        `when`(repository.getQuotes()).thenReturn(quotes)

        val actualQuotes = interactor.loadQuotes()

        assertEquals(quotes, actualQuotes)
        verify(repository).getQuotes()
    }

    @Test
    fun testLoadRemoteQuotes() {
        `when`(repository.getRemoteQuotes()).thenReturn(quotes)

        val actualQuotes = interactor.loadRemoteQuotes()

        assertEquals(quotes, actualQuotes)
        verify(repository).getRemoteQuotes()
    }

}