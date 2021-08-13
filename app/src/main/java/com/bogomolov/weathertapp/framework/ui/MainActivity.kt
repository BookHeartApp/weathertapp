package com.bogomolov.weathertapp.framework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bogomolov.weathertapp.R
import com.bogomolov.weathertapp.framework.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}