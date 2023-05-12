package com.dpfht.android.demo62teknologi.data.model

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.domain.entity.CoordinatesEntity

@Keep
data class Coordinates(
  val latitude: Double? = 0.0,
  val longitude: Double? = 0.0
)

fun Coordinates.toDomain() = CoordinatesEntity(latitude ?: 0.0, longitude ?: 0.0)
