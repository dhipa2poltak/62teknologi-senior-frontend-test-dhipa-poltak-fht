package com.dpfht.android.demo62teknologi.domain.entity

data class ReviewEntity(
  val id: String = "",
  val url: String = "",
  val text: String = "",
  val rating: Double = 0.0,
  val timeCreated: String = "",
  val user: UserEntity? = null
)
