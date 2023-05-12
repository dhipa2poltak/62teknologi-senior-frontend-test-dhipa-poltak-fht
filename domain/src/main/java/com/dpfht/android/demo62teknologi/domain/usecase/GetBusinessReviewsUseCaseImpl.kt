package com.dpfht.android.demo62teknologi.domain.usecase

import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.entity.ReviewsDomain
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository

class GetBusinessReviewsUseCaseImpl(
  private val appRepository: AppRepository
): GetBusinessReviewsUseCase {

  override suspend fun invoke(
    id: String,
    sortBy: String,
    offset: Int,
    limit: Int
  ): Result<ReviewsDomain> {
    return appRepository.getBusinessReviews(id, sortBy, offset, limit)
  }
}
