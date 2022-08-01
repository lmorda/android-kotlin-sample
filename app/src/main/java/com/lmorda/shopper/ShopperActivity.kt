package com.lmorda.shopper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lmorda.shopper.databinding.ActivityMainBinding

class ShopperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        val navView: BottomNavigationView = binding.navView
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() =
        when (FRAGMENT_TAG) {
            STATUS_FRAGMENT -> {}
            INVITE_FRAGMENT -> {
                FRAGMENT_TAG = ""
                navController.navigate(R.id.action_inviteFragment_to_storeFragment)
            }
            else -> super.onBackPressed()
        }

    companion object {
        var FRAGMENT_TAG = ""
    }
}