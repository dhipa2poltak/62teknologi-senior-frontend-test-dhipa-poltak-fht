package com.dpfht.android.demo62teknologi.framework.di.module

import com.dpfht.android.demo62teknologi.framework.BuildConfig
import com.dpfht.android.demo62teknologi.framework.data.core.api.rest.AuthInterceptor
import com.dpfht.android.demo62teknologi.framework.data.core.api.rest.RestService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideClient(): OkHttpClient {
    val httpClientBuilder = OkHttpClient().newBuilder()

    if (BuildConfig.DEBUG) {
      val httpLoggingInterceptor = HttpLoggingInterceptor()
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      httpClientBuilder.addInterceptor(httpLoggingInterceptor)
    }

    httpClientBuilder.addInterceptor(AuthInterceptor())

    return httpClientBuilder.build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build()
  }

  @Provides
  @Singleton
  fun provideRestService(retrofit: Retrofit): RestService {
    return retrofit.create(RestService::class.java)
  }
}
