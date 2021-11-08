package com.aleksandrov.breakingbad.presentation.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleksandrov.breakingbad.domain.CharacterDetailsInteractor
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
import org.mockito.Mockito.anyInt
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: CharacterDetailsInteractor

    @Mock
    private lateinit var characterObserver: Observer<Character>

    @Mock
    private lateinit var progressObserver: Observer<Event<Boolean>>

    @Mock
    private lateinit var errorObserver: Observer<Event<String>>

    @Mock
    private lateinit var character: Character

    private lateinit var schedulers: SchedulersProvider
    private lateinit var viewModel: CharacterDetailsViewModel
    private val IS_START_PROGRESS = true
    private val IS_STOP_PROGRESS = false
    private val ERROR_MESSAGE = "My exception"

    @Before
    fun setUp() {
        schedulers = TestSchedulersProviderImpl()
        viewModel = CharacterDetailsViewModel(interactor, schedulers)

        viewModel.character.observeForever(characterObserver)
        viewModel.progress.observeForever(progressObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun testError() {
        `when`(interactor.loadCharacterById(anyInt())).then {
            throw IllegalAccessException(ERROR_MESSAGE)
        }
        val inOrder =
            Mockito.inOrder(characterObserver, errorObserver, progressObserver, interactor)

        viewModel.loadCharacterById(anyInt())

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadCharacterById(anyInt())
        inOrder.verify(errorObserver).onChanged(Event(ERROR_MESSAGE))
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

    @Test
    fun testLoadCharacterById() {
        `when`(interactor.loadCharacterById(anyInt())).thenReturn(character)
        val inOrder =
            Mockito.inOrder(characterObserver, errorObserver, progressObserver, interactor)

        viewModel.loadCharacterById(anyInt())

        inOrder.verify(progressObserver).onChanged(Event(IS_START_PROGRESS))
        inOrder.verify(interactor).loadCharacterById(anyInt())
        inOrder.verify(characterObserver).onChanged(character)
        inOrder.verify(progressObserver).onChanged(Event(IS_STOP_PROGRESS))
        inOrder.verifyNoMoreInteractions()
    }

}