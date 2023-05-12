package com.dpfht.android.demo62teknologi.domain.entity

data class SearchBusinessDomain(
  val businesses: List<BusinessEntity> = arrayListOf(),
  val total: Int = 0,
)
