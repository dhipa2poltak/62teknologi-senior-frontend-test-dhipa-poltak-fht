package com.dpfht.android.demo62teknologi.feature_splash.di

import com.dpfht.android.demo62teknologi.feature_splash.SplashFragment
import com.dpfht.android.demo62teknologi.framework.di.FragmentScope
import com.dpfht.android.demo62teknologi.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [NavigationComponent::class])
@FragmentScope
interface SplashComponent {

  fun inject(splashFragment: SplashFragment)
}
