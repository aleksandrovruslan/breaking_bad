package com.aleksandrov.breakingbad.presentation.quotes

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.BaseActivity
import com.aleksandrov.breakingbad.utils.showMessage
import javax.inject.Inject

class QuotesActivity : BaseActivity(R.layout.activity_quotes) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: QuotesAdapter

    private lateinit var viewModel: QuotesViewModel
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var recyclerQuotes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setOnRefreshListener { viewModel.loadQuotes(true) }

        recyclerQuotes = findViewById(R.id.recycler_quotes)
        recyclerQuotes.layoutManager = LinearLayoutManager(this)
        recyclerQuotes.adapter = adapter

        viewModel.quotes.observe(this) { adapter.submitList(it) }
        viewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also { progress ->
                swipeLayout.isRefreshing = progress
            }
        }
        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also { message ->
                swipeLayout.showMessage(message)
            }
        }
        viewModel.loadQuotes()
    }

}