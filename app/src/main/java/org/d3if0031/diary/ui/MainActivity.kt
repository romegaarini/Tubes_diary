package org.d3if0031.diary.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.diary.R
import com.example.diary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val baseFragment = setOf(
        R.id.listDiaryFragment,
        R.id.listFavoriteDiaryFragment
    )

    private val hideToolbarOnFragment = setOf(
        0
    )

    private val hideBottomNavOnFragment = setOf(
        R.id.createEditDiaryFragment,
        R.id.detailDiaryFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment)
                .findNavController()

        setupToolbar()
        setupBottomNavigation()
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(baseFragment)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            setupWithNavController(navController)
            setOnItemReselectedListener { }
        }

        navController.addOnDestinationChangedListener { _, dest, _ ->
            binding.toolbar.visibility =
                if (hideToolbarOnFragment.contains(dest.id)) View.GONE else View.VISIBLE

            binding.bottomNavigation.visibility =
                if (hideBottomNavOnFragment.contains(dest.id)) View.GONE else View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}