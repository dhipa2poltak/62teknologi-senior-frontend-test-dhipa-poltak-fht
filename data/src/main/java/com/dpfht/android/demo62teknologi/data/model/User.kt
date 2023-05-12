package com.dpfht.android.demo62teknologi.data.model

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.domain.entity.UserEntity
import com.google.gson.annotations.SerializedName

@Keep
data class User(
  val id: String? = "",
  @SerializedName("profile_url")
  val profileUrl: String? = "",
  @SerializedName("image_url")
  val imageUrl: String? = "",
  val name: String? = ""
)

fun User.toDomain() = UserEntity(
  id ?: "",
  profileUrl ?: "",
  imageUrl ?: "",
  name ?: ""
)
