package com.aleksandrov.breakingbad.presentation.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleksandrov.breakingbad.domain.interactors.CharactersInteractor
import com.aleksandrov.breakingbad.domain.models.Character
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
class CharactersViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: CharactersInteractor

    @Mock
    private lateinit var charactersObserver: Observer<List<Character>>

    @Mock
    private lateinit var progressObserver: Observer<Event<Boolean>>

    @Mock
    private lateinit var errorObserver: Observer<Event<String>>

    @Mock
    private lateinit var characters: List<Character>

    private lateinit var schedulers: SchedulersProvider
    private lateinit var viewModel: CharactersViewModel
    private val IS_START_PROGRESS = true
    private val IS_STOP_PROGRESS = false
    private val ERROR_MESSAGE = "My exception"

    @Before
    fun setUp() {
        schedulers = TestSchedulersProviderImpl()
        viewModel = CharactersViewModel(interactor, schedulers)

        viewModel.characters.observeForever(charactersObserver)
        viewModel.progress.observeForever(progressObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun getError() {
        `when`(interactor.getCharacters()).then { throw IllegalAccessException(ERROR_MESSAGE) }
        val inOrder = Mockito.inOrder(progressObserver, errorObserver, interactor)

        viewModel.loadCharacters()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).getCharacters()
        inOrder.verify(errorObserver).onChanged(Event(ERROR_MESSAGE))
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testLoadCharacters() {
        `when`(interactor.getCharacters()).thenReturn(characters)
        val inOrder = Mockito.inOrder(progressObserver, charactersObserver, interactor)

        viewModel.loadCharacters()

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).getCharacters()
        inOrder.verify(charactersObserver).onChanged(characters)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testLoadCharactersRemote() {
        `when`(interactor.getRemoteCharacters()).thenReturn(characters)
        val inOrder = Mockito.inOrder(progressObserver, charactersObserver, interactor)

        viewModel.loadCharacters(true)

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).getRemoteCharacters()
        inOrder.verify(charactersObserver).onChanged(characters)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

}
