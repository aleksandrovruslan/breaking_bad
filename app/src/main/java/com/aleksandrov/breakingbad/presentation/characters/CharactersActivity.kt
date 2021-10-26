package com.aleksandrov.breakingbad.presentation.characters

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.BaseActivity
import com.aleksandrov.breakingbad.presentation.characterdetails.CHARACTER_ID
import com.aleksandrov.breakingbad.presentation.characterdetails.DetailsActivity
import com.aleksandrov.breakingbad.utils.showError
import javax.inject.Inject

class CharactersActivity : BaseActivity(R.layout.activity_characters), OnItemClickListener {

    @Inject
    lateinit var adapter: CharactersAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: CharactersViewModel
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var recyclerCharacters: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory).get(CharactersViewModel::class.java)

        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setOnRefreshListener { viewModel.loadCharacters(true) }

        recyclerCharacters = findViewById(R.id.recycler_characters)
        recyclerCharacters.adapter = adapter
        adapter.setListener(this)
        recyclerCharacters.layoutManager = LinearLayoutManager(this)

        viewModel.loadCharacters()

        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also(swipeLayout::showError)
        }

        viewModel.characters.observe(this) {
            adapter.submitList(it.toMutableList())
        }

        viewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also { progress ->
                swipeLayout.isRefreshing = progress
            }
        }
    }

    override fun onClick(id: Int) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra(CHARACTER_ID, id)
            startActivity(it)
        }
    }

}