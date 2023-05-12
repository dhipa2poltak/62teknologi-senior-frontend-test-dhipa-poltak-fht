package com.dpfht.android.demo62teknologi.framework.data.core.api.rest

import androidx.annotation.Keep

@Keep
data class ErrorResponse(
  val error: Error?
)

@Keep
data class Error(
  val code: String = "",
  val description: String = ""
)
