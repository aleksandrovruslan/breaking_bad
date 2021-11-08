package com.aleksandrov.breakingbad.presentation.episodedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleksandrov.breakingbad.domain.EpisodeDetailsInteractor
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.utils.Event
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val interactor: EpisodeDetailsInteractor,
    private val schedulers: SchedulersProvider,
) :
    ViewModel() {

    private val _episode: MutableLiveData<Episode> = MutableLiveData()
    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    private val _progress: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()

    val episode: LiveData<Episode> = _episode
    val error: LiveData<Event<String>> = _error
    val progress: LiveData<Event<Boolean>> = _progress

    fun loadEpisodeById(id: Int) {
        if (id >= 0) {
            _progress.value = Event(true)
            disposables.add(
                Single.fromCallable { interactor.loadEpisodeById(id) }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        it?.also { episode ->
                            _episode.value = episode
                        }
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
        disposables.clear()
    }

}