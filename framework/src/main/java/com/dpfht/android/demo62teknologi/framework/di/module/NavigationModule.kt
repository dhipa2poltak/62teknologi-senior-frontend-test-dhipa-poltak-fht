package com.dpfht.android.demo62teknologi.framework.di.module

import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule(private val navigationImpl: NavigationService) {

  @Provides
  @Singleton
  fun provideNavigationService(): NavigationService {
    return navigationImpl
  }
}
