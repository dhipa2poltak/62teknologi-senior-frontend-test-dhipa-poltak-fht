package com.dpfht.android.demo62teknologi.data.repository

import com.dpfht.android.demo62teknologi.data.datasource.AppDataSource
import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.ReviewsDomain
import com.dpfht.android.demo62teknologi.domain.entity.SearchBusinessDomain
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository

class AppRepositoryImpl(
  private val remoteDataSource: AppDataSource
): AppRepository {
  override suspend fun searchBusiness(
    location: String,
    term: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<SearchBusinessDomain> {
    return remoteDataSource.searchBusiness(location, term, sortBy, offset, limit)
  }

  override suspend fun getBusinessById(id: String): Result<BusinessEntity> {
    return remoteDataSource.getBusinessById(id)
  }

  override suspend fun getBusinessReviews(
    id: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<ReviewsDomain> {
    return remoteDataSource.getBusinessReviews(id, sortBy, offset, limit)
  }
}
