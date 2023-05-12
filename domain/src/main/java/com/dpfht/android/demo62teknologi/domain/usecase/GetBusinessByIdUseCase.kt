package com.dpfht.android.demo62teknologi.domain.usecase

import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.Result

interface GetBusinessByIdUseCase {

  suspend operator fun invoke(id: String): Result<BusinessEntity>
}
