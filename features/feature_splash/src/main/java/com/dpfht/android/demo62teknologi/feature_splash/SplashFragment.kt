package com.dpfht.android.demo62teknologi.feature_splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dpfht.android.demo62teknologi.feature_splash.databinding.FragmentSplashBinding
import com.dpfht.android.demo62teknologi.feature_splash.di.DaggerSplashComponent
import com.dpfht.android.demo62teknologi.framework.di.provider.NavigationComponentProvider
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import javax.inject.Inject

class SplashFragment: Fragment() {

  @Inject
  lateinit var navigationService: NavigationService

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val splashComponent = DaggerSplashComponent
      .builder()
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .build()

    splashComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentSplashBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({
      navigateToNextScreen()
    }, 3000)
  }

  private fun navigateToNextScreen() {
    navigationService.navigateToBusinessList()
  }

  override fun onStart() {
    super.onStart()
    (requireActivity() as AppCompatActivity).supportActionBar?.hide()
  }

  override fun onStop() {
    super.onStop()
    (requireActivity() as AppCompatActivity).supportActionBar?.show()
  }
}
