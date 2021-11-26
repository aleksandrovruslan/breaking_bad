package com.aleksandrov.breakingbad.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleksandrov.breakingbad.domain.interactors.CharactersInteractor
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val interactor: CharactersInteractor,
    private val schedulers: SchedulersProvider,
) :
    ViewModel() {

    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    private val _progress: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var _reload = true

    val characters: LiveData<List<Character>> = _characters
    val error: LiveData<Event<String>> = _error
    val progress: LiveData<Event<Boolean>> = _progress

    /**
     * Загрузить персонажей
     */
    fun loadCharacters(reload: Boolean = false) {
        if (reload || _reload) {
            _progress.value = Event(true)
            disposables.add(
                Single.fromCallable {
                    if (reload) {
                        interactor.getRemoteCharacters()
                    } else {
                        interactor.getCharacters().let {
                            if (it == null || it.isEmpty()) {
                                interactor.getRemoteCharacters()
                            } else {
                                it
                            }
                        }
                    }
                }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe(
                        {
                            it?.also { characters ->
                                _characters.value = characters
                            }
                            _progress.value = Event(false)
                        },
                        {
                            it.message?.also { message ->
                                _error.value = Event(message)
                            }
                            _progress.value = Event(false)
                        }
                    )
            )
            _reload = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}