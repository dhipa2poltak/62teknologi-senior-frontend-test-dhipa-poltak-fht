package com.dpfht.android.demo62teknologi.data.model

import androidx.annotation.Keep

import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.google.gson.annotations.SerializedName

@Keep
data class Business(
  val id: String? = "",
  val alias: String? = "",
  val name: String? = "",
  @SerializedName("image_url")
  val imageUrl: String? = "",
  @SerializedName("is_claimed")
  val isClaimed: Boolean? = false,
  @SerializedName("is_closed")
  val isClosed: Boolean? = false,
  val url: String? = "",
  @SerializedName("review_count")
  val reviewCount: Int? = 0,
  val categories: List<Category>? = arrayListOf(),
  val rating: Double? = 0.0,
  val coordinates: Coordinates? = null,
  val transactions: List<String>? = arrayListOf(),
  val price: String? = "",
  val location: Location? = null,
  val phone: String? = "",
  @SerializedName("display_phone")
  val displayPhone: String? = "",
  val distance: Double? = 0.0,
  val photos: List<String>? = arrayListOf()
)

fun Business.toDomain() = BusinessEntity(
  id ?: "",
  alias ?: "",
  name ?: "",
  imageUrl ?: "",
  url ?: "",
  reviewCount ?: 0,
  categories?.map { it.toDomain() } ?: arrayListOf(),
  rating ?: 0.0,
  coordinates?.toDomain(),
  transactions ?: arrayListOf(),
  price ?: "",
  location?.toDomain(),
  phone ?: "",
  displayPhone ?: "",
  distance ?: 0.0,
  photos ?: arrayListOf()
)
