package com.dpfht.android.demo62teknologi.data.model.response

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.data.model.Review
import com.dpfht.android.demo62teknologi.data.model.toDomain
import com.dpfht.android.demo62teknologi.domain.entity.ReviewsDomain
import com.google.gson.annotations.SerializedName

@Keep
data class ReviewsResponse(
  val reviews: List<Review>? = arrayListOf(),
  val total: Int? = 0,
  @SerializedName("possible_languages")
  val possibleLanguages: List<String>? = arrayListOf()
)

fun ReviewsResponse.toDomain() = ReviewsDomain(
  reviews?.map { it.toDomain() } ?: arrayListOf(),
  total ?: 0,
  possibleLanguages ?: arrayListOf()
)
