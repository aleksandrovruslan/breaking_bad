package com.aleksandrov.breakingbad.presentation.characters

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.characterdetails.CHARACTER_ID
import com.aleksandrov.breakingbad.presentation.characterdetails.DetailsActivity
import javax.inject.Inject

class CharactersActivity : AppCompatActivity(), OnItemClickListener {

    @Inject
    lateinit var viewModel: CharactersViewModel

    @Inject
    lateinit var adapter: CharactersAdapter

    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var recyclerCharacters: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setOnRefreshListener { onPullToRefresh() }

        recyclerCharacters = findViewById(R.id.recycler_characters)
        recyclerCharacters.adapter = adapter
        adapter.setListener(this)
        recyclerCharacters.layoutManager = LinearLayoutManager(this)

        viewModel.loadCharacters()

        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also { error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
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

    private fun onPullToRefresh() {
        viewModel.loadCharacters(true)
    }

    override fun onClick(id: Int) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra(CHARACTER_ID, id)
            startActivity(it)
        }
    }

}