package com.dpfht.android.demo62teknologi.framework.data.core.api.rest

import com.dpfht.android.demo62teknologi.data.Constants
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor: Interceptor {
  override fun intercept(chain: Chain): Response {
    val original = chain.request()

    val requestBuilder = original.newBuilder()
      .method(original.method, original.body)
      .header("accept", "application/json")
      .header("Authorization", "Bearer ${Constants.API_KEY}")

    val request = requestBuilder.build()

    return chain.proceed(request)
  }
}
