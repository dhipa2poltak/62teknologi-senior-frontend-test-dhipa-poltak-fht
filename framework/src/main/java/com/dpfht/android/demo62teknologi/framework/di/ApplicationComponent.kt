package com.dpfht.android.demo62teknologi.framework.di

import com.dpfht.android.demo62teknologi.domain.repository.AppRepository
import com.dpfht.android.demo62teknologi.framework.di.module.ApplicationModule
import com.dpfht.android.demo62teknologi.framework.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

  fun getAppRepository(): AppRepository
}
