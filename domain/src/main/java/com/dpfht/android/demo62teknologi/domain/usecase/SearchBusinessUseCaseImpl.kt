package com.dpfht.android.demo62teknologi.domain.usecase

import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.SearchBusinessDomain
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository

class SearchBusinessUseCaseImpl(
  private val appRepository: AppRepository
): SearchBusinessUseCase {

  override suspend fun invoke(
    location: String,
    term: String,
    prices: List<Int>,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<SearchBusinessDomain> {
    return appRepository.searchBusiness(location, term, prices, sortBy, offset, limit)
  }
}
