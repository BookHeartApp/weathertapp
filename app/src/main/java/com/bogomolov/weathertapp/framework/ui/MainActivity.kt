package com.bogomolov.weathertapp.framework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.bogomolov.weathertapp.R
import com.bogomolov.weathertapp.framework.ui.contacs.ContactsFragment
import com.bogomolov.weathertapp.framework.ui.history.HistoryFragment
import com.bogomolov.weathertapp.framework.ui.list_of_cities.ListOfCitiesFragment
import com.bogomolov.weathertapp.framework.ui.maps.MapsFragment
import com.bogomolov.weathertapp.framework.ui.threads.ThreadsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListOfCitiesFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_threads -> {
                openFragment(ThreadsFragment.newInstance())
                true
            }
            R.id.menu_history -> {
                openFragment(HistoryFragment.newInstance())
                true
            }
            R.id.menu_contacts -> {
                openFragment(ContactsFragment.newInstance())
                true
            }
            R.id.menu_google_maps -> {
                openFragment(MapsFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}