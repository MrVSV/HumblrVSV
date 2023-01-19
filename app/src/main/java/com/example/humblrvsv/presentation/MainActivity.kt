package com.example.humblrvsv.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.humblrvsv.R
import com.example.humblrvsv.SHARED_SELECTED_TAB_NAME
import com.example.humblrvsv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.onBoardingFragment || destination.id == R.id.authFragment) {
                navView.visibility = View.GONE
            } else navView.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        Log.d("onDestroy", "activity")
        val prefs = applicationContext.getSharedPreferences(SHARED_SELECTED_TAB_NAME, MODE_PRIVATE)
        prefs.edit().clear().apply()
        super.onDestroy()
    }
}