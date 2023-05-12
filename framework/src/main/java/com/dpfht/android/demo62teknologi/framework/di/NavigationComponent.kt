package com.dpfht.android.demo62teknologi.framework.di

import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import com.dpfht.android.demo62teknologi.framework.di.module.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent {

  fun getNavigationService(): NavigationService
}
