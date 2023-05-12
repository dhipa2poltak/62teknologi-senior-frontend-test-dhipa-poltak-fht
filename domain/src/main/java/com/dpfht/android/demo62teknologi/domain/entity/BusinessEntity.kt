package com.dpfht.android.demo62teknologi.domain.entity

data class BusinessEntity(
  val id: String = "",
  val alias: String = "",
  val name: String = "",
  val imageUrl: String = "",
  val url: String = "",
  val reviewCount: Int = 0,
  val categories: List<CategoryEntity> = arrayListOf(),
  val rating: Double = 0.0,
  val coordinates: CoordinatesEntity? = null,
  val transactions: List<String> = arrayListOf(),
  val price: String = "",
  val location: LocationEntity? = null,
  val phone: String = "",
  val displayPhone: String = "",
  val distance: Double = 0.0,
  val photos: List<String> = arrayListOf()
)
