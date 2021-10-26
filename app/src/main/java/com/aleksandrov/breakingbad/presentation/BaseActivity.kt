package com.aleksandrov.breakingbad.presentation

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.presentation.characters.CharactersActivity
import com.aleksandrov.breakingbad.presentation.deaths.DeathsActivity
import com.aleksandrov.breakingbad.presentation.episodes.EpisodesActivity
import com.aleksandrov.breakingbad.presentation.quotes.QuotesActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.characters -> openActivity(CharactersActivity::class.java)
                R.id.deaths -> openActivity(DeathsActivity::class.java)
                R.id.episodes -> openActivity(EpisodesActivity::class.java)
                R.id.quotes -> openActivity(QuotesActivity::class.java)
                else -> false
            }
        }
    }

    private fun openActivity(clazz: Class<out BaseActivity>): Boolean {
        javaClass.also {
            if (it != clazz) {
                val intent = Intent(this, clazz)
                startActivity(intent)
            }
        }
        return true
    }

}