package com.aleksandrov.breakingbad.presentation.episodedetails

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.BaseActivity
import com.aleksandrov.breakingbad.utils.showError
import javax.inject.Inject

const val EPISODE_ID = "EPISODE_ID"

class EpisodeDetailsActivity : BaseActivity(R.layout.activity_episode_details) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: EpisodeDetailsViewModel
    private lateinit var title: TextView
    private lateinit var season: TextView
    private lateinit var airDate: TextView
    private lateinit var characters: TextView
    private lateinit var episode: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory).get(EpisodeDetailsViewModel::class.java)

        val id: Int = intent.getIntExtra(EPISODE_ID, -1)
        viewModel.loadEpisodeById(id)

        title = findViewById(R.id.title)
        season = findViewById(R.id.season)
        airDate = findViewById(R.id.air_date)
        characters = findViewById(R.id.characters)
        episode = findViewById(R.id.episode)
        progressBar = findViewById(R.id.progress)

        viewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also { progress ->
                progressBar.visibility = if (progress) View.VISIBLE else View.INVISIBLE
            }
        }
        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also(title::showError)
        }
        viewModel.episode.observe(this) {
            title.text = it.title
            season.text = it.season
            airDate.text = it.air_date
            characters.text = it.characters.joinToString(", ")
            episode.text = it.episode
        }
    }

}