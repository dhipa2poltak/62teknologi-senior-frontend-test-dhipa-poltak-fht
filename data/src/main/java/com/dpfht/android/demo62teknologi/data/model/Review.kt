package com.dpfht.android.demo62teknologi.data.model

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.domain.entity.ReviewEntity
import com.google.gson.annotations.SerializedName

@Keep
data class Review(
  val id: String? = "",
  val url: String? = "",
  val text: String? = "",
  val rating: Double? = 0.0,
  @SerializedName("time_created")
  val timeCreated: String? = "",
  val user: User? = null
)

fun Review.toDomain() = ReviewEntity(
  id ?: "",
  url ?: "",
  text ?: "",
  rating ?: 0.0,
  timeCreated ?: "",
  user?.toDomain()
)
