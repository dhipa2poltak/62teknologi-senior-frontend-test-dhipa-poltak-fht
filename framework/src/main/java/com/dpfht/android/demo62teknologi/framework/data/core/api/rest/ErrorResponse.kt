package com.dpfht.android.demo62teknologi.framework.data.core.api.rest

data class ErrorResponse(
  val error: Error?
)

data class Error(
  val code: String = "",
  val description: String = ""
)
