package com.aleksandrov.breakingbad.presentation.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleksandrov.breakingbad.domain.interactors.QuotesInteractor
import com.aleksandrov.breakingbad.domain.models.Quote
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val interactor: QuotesInteractor,
    private val schedulers: SchedulersProvider,
) : ViewModel() {

    private val _quotes: MutableLiveData<List<Quote>> = MutableLiveData()
    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    private val _progress: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var _reload = true

    val quotes: LiveData<List<Quote>> = _quotes
    val error: LiveData<Event<String>> = _error
    val progress: LiveData<Event<Boolean>> = _progress

    fun loadQuotes(reload: Boolean = false) {
        if (reload || _reload) {
            _progress.value = Event(true)
            disposables.add(
                Single.fromCallable {
                    if (reload) {
                        interactor.loadRemoteQuotes()
                    } else {
                        interactor.loadQuotes().let {
                            if (it == null || it.isEmpty()) {
                                interactor.loadRemoteQuotes()
                            } else {
                                it
                            }
                        }
                    }
                }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        it?.also { quotes ->
                            _quotes.value = quotes
                        }
                        _progress.value = Event(false)
                    }, {
                        _error.value = Event(it.message.toString())
                        _progress.value = Event(false)
                    })
            )
            _reload = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}