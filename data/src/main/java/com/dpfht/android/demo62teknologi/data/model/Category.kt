package com.dpfht.android.demo62teknologi.data.model

import androidx.annotation.Keep
import com.dpfht.android.demo62teknologi.domain.entity.CategoryEntity

@Keep
data class Category(
  val alias: String? = "",
  val title: String? = ""
)

fun Category.toDomain() = CategoryEntity(alias ?: "", title ?: "")
