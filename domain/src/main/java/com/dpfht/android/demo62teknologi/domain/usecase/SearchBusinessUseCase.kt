package com.dpfht.android.demo62teknologi.domain.usecase

import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.SearchBusinessDomain

interface SearchBusinessUseCase {

  suspend operator fun invoke(
    location: String,
    term: String,
    prices: List<Int>,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<SearchBusinessDomain>
}
