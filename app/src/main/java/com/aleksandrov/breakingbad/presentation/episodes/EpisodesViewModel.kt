package com.aleksandrov.breakingbad.presentation.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleksandrov.breakingbad.domain.EpisodesInteractor
import com.aleksandrov.breakingbad.models.Episode
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val interactor: EpisodesInteractor,
    private val schedulers: SchedulersProvider,
) :
    ViewModel() {

    private val _episodes: MutableLiveData<List<Episode>> = MutableLiveData()
    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    private val _progress: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var _reload = true

    val episodes: LiveData<List<Episode>> = _episodes
    val error: LiveData<Event<String>> = _error
    val progress: LiveData<Event<Boolean>> = _progress

    fun loadEpisodes(reload: Boolean = false) {
        if (reload || _reload) {
            _progress.value = Event(true)
            disposables.add(
                Single.fromCallable {
                    if (reload) {
                        interactor.loadRemoteEpisodes()
                    } else {
                        interactor.loadEpisodes()
                    }
                }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        it?.also { episodes ->
                            _episodes.value = episodes
                            _progress.value = Event(false)
                        }
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