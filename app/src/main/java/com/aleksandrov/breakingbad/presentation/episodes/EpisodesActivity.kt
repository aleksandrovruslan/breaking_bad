package com.aleksandrov.breakingbad.presentation.episodes

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.BaseActivity
import com.aleksandrov.breakingbad.presentation.episodedetails.EPISODE_ID
import com.aleksandrov.breakingbad.presentation.episodedetails.EpisodeDetailsActivity
import com.aleksandrov.breakingbad.utils.showError
import javax.inject.Inject

class EpisodesActivity : BaseActivity(R.layout.activity_episodes), OnEpisodeItemClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: EpisodesAdapter

    private lateinit var viewModel: EpisodesViewModel
    private lateinit var episodesRecycler: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory).get(EpisodesViewModel::class.java)

        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setOnRefreshListener { viewModel.loadEpisodes(true) }

        episodesRecycler = findViewById(R.id.recycler_episodes)
        episodesRecycler.layoutManager = LinearLayoutManager(this)
        episodesRecycler.adapter = adapter
        adapter.setListener(this)

        viewModel.episodes.observe(this) {
            adapter.submitList(it)
        }
        viewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also { progress -> swipeLayout.isRefreshing = progress }
        }
        viewModel.error.observe(this) { it.getContentIfNotHandled()?.also(swipeLayout::showError) }
        viewModel.loadEpisodes()
    }

    override fun onClick(id: Int) {
        Intent(this, EpisodeDetailsActivity::class.java).also {
            it.putExtra(EPISODE_ID, id)
            startActivity(it)
        }
    }

}