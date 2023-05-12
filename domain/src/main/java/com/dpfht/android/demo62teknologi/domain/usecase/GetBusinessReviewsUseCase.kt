package com.dpfht.android.demo62teknologi.domain.usecase

import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.ReviewsDomain

interface GetBusinessReviewsUseCase {

  suspend operator fun invoke(
    id: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<ReviewsDomain>
}
