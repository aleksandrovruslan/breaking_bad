package com.aleksandrov.breakingbad.presentation.deaths

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.BaseActivity
import com.aleksandrov.breakingbad.utils.showError
import javax.inject.Inject

class DeathsActivity : BaseActivity(R.layout.activity_deaths) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var deathCountViewModel: DeathCountViewModel
    private lateinit var message: TextView
    private lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        deathCountViewModel = ViewModelProvider(this, factory).get(DeathCountViewModel::class.java)

        message = findViewById(R.id.message)
        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setOnRefreshListener { deathCountViewModel.loadDeathCount(true) }

        deathCountViewModel.loadDeathCount()

        deathCountViewModel.deathCount.observe(this) {
            message.text = "death count - $it"
        }
        deathCountViewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also {
                swipeLayout.isRefreshing = it
            }
        }
        deathCountViewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also(swipeLayout::showError)
        }
    }

}