package com.aleksandrov.breakingbad.presentation.deaths

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleksandrov.breakingbad.domain.BBInteractor
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DeathCountViewModel @Inject constructor(
    private val interactor: BBInteractor,
    private val schedulers: SchedulersProvider,
) : ViewModel() {

    private val _deathCount: MutableLiveData<Int> = MutableLiveData()
    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    private val _progress: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()

    val deathCount: LiveData<Int> = _deathCount
    val error: LiveData<Event<String>> = _error
    val progress: LiveData<Event<Boolean>> = _progress

    /**
     * Загрузить количество смертей
     */
    fun loadDeathCount() {
        _progress.value = Event(true)
        disposables.add(Single.fromCallable { interactor.getDeathCount() }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                {
                    it?.deathCount.also { _deathCount.value = it }
                    _progress.value = Event(false)
                }, {
                    _error.value = Event(it.message.toString())
                    _progress.value = Event(false)
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}