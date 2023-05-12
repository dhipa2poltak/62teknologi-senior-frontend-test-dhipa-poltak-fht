package com.dpfht.android.demo62teknologi.domain.entity

data class ReviewsDomain(
  val reviews: List<ReviewEntity> = arrayListOf(),
  val total: Int = 0,
  val possibleLanguages: List<String> = arrayListOf()
)
