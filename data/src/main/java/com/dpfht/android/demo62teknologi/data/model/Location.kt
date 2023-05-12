package com.dpfht.android.demo62teknologi.data.model

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.domain.entity.LocationEntity
import com.google.gson.annotations.SerializedName

@Keep
data class Location(
  val address1: String? = "",
  val address2: String? = "",
  val address3: String? = "",
  val city: String? = "",
  @SerializedName("zip_code")
  val zipCode: String? = "",
  val country: String? = "",
  val state: String? = "",
  @SerializedName("display_address")
  val displayAddress: List<String>? = arrayListOf(),
  @SerializedName("cross_streets")
  val crossStreets: String? = ""
)

fun Location.toDomain() = LocationEntity(
  address1 ?: "",
  address2 ?: "",
  address3 ?: "",
  city ?: "",
  zipCode ?: "",
  country ?: "",
  state ?: "",
  displayAddress ?: arrayListOf()
)
