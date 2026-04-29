package com.vishal.cycleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vishal.cycleapp.ui.home.HomeFragment
import com.vishal.cycleapp.ui.insights.InsightsFragment
import com.vishal.cycleapp.ui.track.TrackFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Load Insights as default screen (matching your design)
        if (savedInstanceState == null) {
            loadFragment(InsightsFragment())
            bottomNav.selectedItemId = R.id.nav_insights
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_track -> {
                    loadFragment(TrackFragment())
                    true
                }
                R.id.nav_insights -> {
                    loadFragment(InsightsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}