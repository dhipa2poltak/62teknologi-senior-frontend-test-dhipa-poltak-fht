package com.dpfht.android.demo62teknologi.framework.di.module

import android.content.Context
import com.dpfht.android.demo62teknologi.data.datasource.AppDataSource
import com.dpfht.android.demo62teknologi.data.repository.AppRepositoryImpl
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository
import com.dpfht.android.demo62teknologi.framework.data.core.api.rest.RestService
import com.dpfht.android.demo62teknologi.framework.data.datasource.RemoteDataSourceImpl
import com.dpfht.android.demo62teknologi.framework.di.ApplicationContext
import com.dpfht.android.demo62teknologi.framework.di.RemoteDataSourceQ
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

  @Provides
  @ApplicationContext
  fun provideContext(): Context {
    return context
  }

  @Provides
  @Singleton
  @RemoteDataSourceQ
  fun provideRemoteDataSource(restService: RestService): AppDataSource {
    return RemoteDataSourceImpl(restService)
  }

  @Provides
  @Singleton
  fun provideAppRepository(
    @RemoteDataSourceQ remoteDataSource: AppDataSource
  ): AppRepository {
    return AppRepositoryImpl(remoteDataSource)
  }
}
