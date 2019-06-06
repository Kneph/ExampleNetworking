package com.example.examplenetworking

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.examplenetworking.adapters.RepositoryAdapter
import com.example.examplenetworking.fragments.DashboardFragment
import com.example.examplenetworking.fragments.HomeFragment
import com.example.examplenetworking.fragments.NotificationsFragment
import com.example.examplenetworking.models.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment = HomeFragment()
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = HomeFragment()
            }
            R.id.navigation_dashboard -> {
                fragment = DashboardFragment()
            }
            R.id.navigation_notifications -> {
                fragment = NotificationsFragment()
            }
        }
        loadFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment())
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        return if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            true
        } else {
            false
        }
    }
}
