package com.gzeinnumer.daggerpracticekt.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.gzeinnumer.daggerpracticekt.BaseActivity
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainActivity"

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: created")
        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show()

        initFragment()

//        initScopeExample()
    }


    private fun initFragment() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                true
            }
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                } else {
                    false
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profil -> {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.main, true).build()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, navOptions)
            }
            R.id.nav_posts -> if (isValidDestination(R.id.postScreen)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postScreen)
            }
        }
        item.isCheckable = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawerLayout
        )
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment)
            .currentDestination!!.id
    }

//    @Inject
//    @Named("app_login")
//    lateinit var responseLogin1: ResponseLogin
//
//    @Inject
//    @Named("auth_login")
//    lateinit var responseLogin2: ResponseLogin
//
//    private fun initScopeExample() {
//        Log.d(TAG, "initScopeExample: $responseLogin1")
//        Log.d(TAG, "initScopeExample: $responseLogin2")
//    }
}
