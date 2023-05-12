package com.dpfht.android.demo62teknologi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.dpfht.android.demo62teknologi.R.id
import com.dpfht.android.demo62teknologi.databinding.ActivityMainBinding
import com.dpfht.android.demo62teknologi.framework.di.DaggerNavigationComponent
import com.dpfht.android.demo62teknologi.framework.di.NavigationComponent
import com.dpfht.android.demo62teknologi.framework.di.module.NavigationModule
import com.dpfht.android.demo62teknologi.framework.di.provider.NavigationComponentProvider
import com.dpfht.android.demo62teknologi.navigation.NavigationServiceImpl

class MainActivity : AppCompatActivity(), NavigationComponentProvider {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController

  private lateinit var navigationComponent: NavigationComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navHostFragment =
      supportFragmentManager.findFragmentById(id.my_nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController

    val appBarConfiguration = AppBarConfiguration(
      setOf(id.business_list_fragment)
    )

    navigationComponent = DaggerNavigationComponent
      .builder()
      .navigationModule(NavigationModule(NavigationServiceImpl(navController)))
      .build()

    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }

  override fun provideNavigationComponent(): NavigationComponent {
    return navigationComponent
  }
}
