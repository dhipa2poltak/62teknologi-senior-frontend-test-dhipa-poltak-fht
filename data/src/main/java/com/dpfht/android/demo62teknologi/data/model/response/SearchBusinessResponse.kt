package com.dpfht.android.demo62teknologi.data.model.response

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.data.model.Business
import com.dpfht.android.demo62teknologi.data.model.toDomain
import com.dpfht.android.demo62teknologi.domain.entity.SearchBusinessDomain

@Keep
data class SearchBusinessResponse(
  val businesses: List<Business>? = arrayListOf(),
  val total: Int? = 0,
  val region: Region? = null
)

data class Region(
  val center: Center? = null
)

data class Center(
  val longitude: Double? = 0.0,
  val latitude: Double? = 0.0
)

fun SearchBusinessResponse.toDomain() = SearchBusinessDomain(
  businesses?.map { it.toDomain() } ?: arrayListOf(),
  total ?: 0
)
