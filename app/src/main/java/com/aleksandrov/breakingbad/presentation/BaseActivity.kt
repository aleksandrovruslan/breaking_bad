package com.aleksandrov.breakingbad.presentation

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.presentation.characters.CharactersActivity
import com.aleksandrov.breakingbad.presentation.deaths.DeathsActivity
import com.aleksandrov.breakingbad.presentation.episodes.EpisodesActivity
import com.aleksandrov.breakingbad.presentation.quotes.QuotesActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

const val MENU_ITEM_SELECTED = "MENU_ITEM_SELECTED"

abstract class BaseActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.characters -> openActivity(CharactersActivity::class.java, item.itemId)
                R.id.deaths -> openActivity(DeathsActivity::class.java, item.itemId)
                R.id.episodes -> openActivity(EpisodesActivity::class.java, item.itemId)
                R.id.quotes -> openActivity(QuotesActivity::class.java, item.itemId)
                else -> false
            }
        }

        intent.getIntExtra(MENU_ITEM_SELECTED, -1).let { itemId ->
            if (itemId > 0) {
                bottomNavigation.menu.findItem(itemId)?.let { itemMenu ->
                    itemMenu.isChecked = true
                }
            }
        }
    }

    private fun openActivity(clazz: Class<out BaseActivity>, @IdRes itemId: Int): Boolean {
        javaClass.also {
            if (it != clazz) {
                val intent = Intent(this, clazz)
                intent.putExtra(MENU_ITEM_SELECTED, itemId)
                startActivity(intent)
            }
        }
        return true
    }

}