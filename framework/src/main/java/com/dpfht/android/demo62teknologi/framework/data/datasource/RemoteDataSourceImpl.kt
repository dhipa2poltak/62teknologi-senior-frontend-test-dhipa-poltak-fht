package com.dpfht.android.demo62teknologi.framework.data.datasource

import com.dpfht.android.demo62teknologi.data.datasource.AppDataSource
import com.dpfht.android.demo62teknologi.data.model.response.toDomain
import com.dpfht.android.demo62teknologi.data.model.toDomain
import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.SearchBusinessDomain
import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.ReviewsDomain
import com.dpfht.android.demo62teknologi.framework.data.core.api.rest.RestService
import com.dpfht.android.demo62teknologi.framework.data.core.api.rest.safeApiCall
import kotlinx.coroutines.Dispatchers

class RemoteDataSourceImpl(
  private val restService: RestService
): AppDataSource {

  override suspend fun searchBusiness(
    location: String,
    term: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<SearchBusinessDomain> {
    return safeApiCall(Dispatchers.IO) { restService.searchBusiness(location, term, sortBy, offset, limit).toDomain() }
  }

  override suspend fun getBusinessById(id: String): Result<BusinessEntity> {
    return safeApiCall(Dispatchers.IO) { restService.getBusinessById(id).toDomain() }
  }

  override suspend fun getBusinessReviews(
    id: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<ReviewsDomain> {
    return safeApiCall(Dispatchers.IO) { restService.getBusinessReviews(id, sortBy, offset, limit).toDomain() }
  }
}
