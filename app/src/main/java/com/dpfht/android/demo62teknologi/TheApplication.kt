package com.dpfht.android.demo62teknologi

import android.app.Application
import com.dpfht.android.demo62teknologi.framework.di.ApplicationComponent
import com.dpfht.android.demo62teknologi.framework.di.DaggerApplicationComponent
import com.dpfht.android.demo62teknologi.framework.di.module.ApplicationModule
import com.dpfht.android.demo62teknologi.framework.di.module.NetworkModule
import com.dpfht.android.demo62teknologi.framework.di.provider.ApplicationComponentProvider

class TheApplication: Application(), ApplicationComponentProvider {

  private lateinit var applicationComponent: ApplicationComponent

  override fun onCreate() {
    super.onCreate()

    applicationComponent = DaggerApplicationComponent
      .builder()
      .applicationModule(ApplicationModule(this))
      .networkModule(NetworkModule())
      .build()
  }

  override fun provideApplicationComponent(): ApplicationComponent {
    return applicationComponent
  }
}
