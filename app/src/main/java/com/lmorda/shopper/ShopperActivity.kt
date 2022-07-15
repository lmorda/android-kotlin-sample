package com.lmorda.shopper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lmorda.shopper.databinding.ActivityMainBinding

class ShopperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.storeFragment -> navView.visibility = View.VISIBLE
                R.id.buyAgainFragment -> navView.visibility = View.VISIBLE
                R.id.ordersFragment -> navView.visibility = View.VISIBLE
                R.id.settingsFragment -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
    }

}