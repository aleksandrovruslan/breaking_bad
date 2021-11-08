package com.aleksandrov.breakingbad.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleksandrov.breakingbad.domain.models.DeathCount
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
class DeathsInteractorTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: BBRepository

    @Mock
    private lateinit var count: DeathCount
    private lateinit var interactor: DeathsInteractor

    @Before
    fun setUp() {
        interactor = DeathsInteractor(repository)
    }

    @Test
    fun testDeathCount() {
        `when`(repository.getDeathCount()).thenReturn(count)

        val actualResult = interactor.getDeathCount()

        assertEquals(count, actualResult)
        verify(repository).getDeathCount()
    }

    @Test
    fun testRemoteDeathCount() {
        `when`(repository.getRemoteDeathCount()).thenReturn(count)

        val actualResult = interactor.getRemoteDeathCount()

        assertEquals(count, actualResult)
        verify(repository).getRemoteDeathCount()
    }

}