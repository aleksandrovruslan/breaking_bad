package com.aleksandrov.breakingbad.presentation.deaths

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.aleksandrov.breakingbad.presentation.BaseActivity
import javax.inject.Inject

class DeathsActivity : BaseActivity(R.layout.activity_deaths) {

    @Inject
    lateinit var deathCountViewModel: DeathCountViewModel

    private lateinit var message: TextView
    private lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        message = findViewById(R.id.message)
        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setOnRefreshListener { deathCountViewModel.loadDeathCount() }

        deathCountViewModel.deathCount.observe(this) {
            message.text = "death count - $it"
        }
        deathCountViewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also {
                swipeLayout.isRefreshing = it
            }
        }
        deathCountViewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

}