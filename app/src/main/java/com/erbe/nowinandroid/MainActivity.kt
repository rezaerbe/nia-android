package com.erbe.nowinandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.erbe.nowinandroid.core.common.design.WindowSizeClass
import com.erbe.nowinandroid.core.common.design.computeWindowSizeClasses
import com.erbe.nowinandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowSize()
    }

    private fun setupWindowSize() {
        when (computeWindowSizeClasses(this)) {
            WindowSizeClass.COMPACT ->
                binding.bottomNavigationView?.setupWithNavController(navController)
            WindowSizeClass.MEDIUM ->
                binding.navigationRailView?.setupWithNavController(navController)
            WindowSizeClass.EXPANDED ->
                binding.navigationView?.setupWithNavController(navController)
        }
    }
}