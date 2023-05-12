package com.dpfht.android.demo62teknologi.domain.usecase

import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.Result
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository

class GetBusinessByIdUseCaseImpl(
  private val appRepository: AppRepository
): GetBusinessByIdUseCase {

  override suspend fun invoke(id: String): Result<BusinessEntity> {
    return appRepository.getBusinessById(id)
  }
}
