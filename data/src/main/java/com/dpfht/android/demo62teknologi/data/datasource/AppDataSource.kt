package com.dpfht.android.demo62teknologi.data.datasource

import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.ReviewsDomain
import com.dpfht.android.demo62teknologi.domain.entity.SearchBusinessDomain

interface AppDataSource {
  suspend fun searchBusiness(
    location: String,
    term: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<SearchBusinessDomain>

  suspend fun getBusinessById(id: String): Result<BusinessEntity>

  suspend fun getBusinessReviews(
    id: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<ReviewsDomain>
}
