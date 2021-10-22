package com.aleksandrov.breakingbad.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleksandrov.breakingbad.domain.DetailsInteractor
import com.aleksandrov.breakingbad.models.Character
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val interactor: DetailsInteractor,
    private val schedulers: SchedulersProvider,
) :
    ViewModel() {

    private val _character: MutableLiveData<Character> = MutableLiveData()
    private val _progress: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    private var _id = -1
    private val disposables: CompositeDisposable = CompositeDisposable()

    val character: LiveData<Character> = _character
    val progress: LiveData<Event<Boolean>> = _progress
    val error: LiveData<Event<String>> = _error

    /**
     * Загрузить песонажа по id
     */
    fun loadCharacterById(id: Int) {
        if (id >= 0 && id != _id) {
            _progress.value = Event(true)
            disposables.add(
                Single.fromCallable {
                    interactor.loadCharacterById(id)
                }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        it?.also { _character.value = it }
                        _progress.value = Event(false)
                    }, {
                        _error.value = Event(it.message.toString())
                        _progress.value = Event(false)
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}