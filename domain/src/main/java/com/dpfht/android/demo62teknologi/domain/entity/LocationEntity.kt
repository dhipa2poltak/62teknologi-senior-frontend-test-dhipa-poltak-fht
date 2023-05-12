package com.dpfht.android.demo62teknologi.domain.entity

data class LocationEntity(
  val address1: String = "",
  val address2: String = "",
  val address3: String = "",
  val city: String = "",
  val zipCode: String = "",
  val country: String = "",
  val state: String = "",
  val displayAddress: List<String> = arrayListOf()
)
