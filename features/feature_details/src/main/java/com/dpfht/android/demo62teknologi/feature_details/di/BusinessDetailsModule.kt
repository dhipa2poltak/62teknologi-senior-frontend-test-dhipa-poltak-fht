package com.dpfht.android.demo62teknologi.feature_details.di

import com.dpfht.android.demo62teknologi.domain.entity.ReviewEntity
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository
import com.dpfht.android.demo62teknologi.domain.usecase.GetBusinessByIdUseCase
import com.dpfht.android.demo62teknologi.domain.usecase.GetBusinessByIdUseCaseImpl
import com.dpfht.android.demo62teknologi.domain.usecase.GetBusinessReviewsUseCase
import com.dpfht.android.demo62teknologi.domain.usecase.GetBusinessReviewsUseCaseImpl
import com.dpfht.android.demo62teknologi.feature_details.view.BusinessDetailsViewModel
import com.dpfht.android.demo62teknologi.feature_details.view.adapter.ReviewAdapter
import com.dpfht.android.demo62teknologi.framework.di.FragmentScope
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides

@Module
class BusinessDetailsModule {

  @Provides
  @FragmentScope
  fun provideReviews(): ArrayList<ReviewEntity> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideGetBusinessByIdUseCase(appRepository: AppRepository): GetBusinessByIdUseCase {
    return GetBusinessByIdUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideGetBusinessReviewsUseCase(appRepository: AppRepository): GetBusinessReviewsUseCase {
    return GetBusinessReviewsUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideBusinessListViewModel(
    reviews: ArrayList<ReviewEntity>,
    getBusinessByIdUseCase: GetBusinessByIdUseCase,
    getBusinessReviewsUseCase: GetBusinessReviewsUseCase,
    navigationService: NavigationService
  ): BusinessDetailsViewModel {
    return BusinessDetailsViewModel(reviews, getBusinessByIdUseCase, getBusinessReviewsUseCase, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideReviewAdapter(reviews: ArrayList<ReviewEntity>): ReviewAdapter {
    return ReviewAdapter(reviews)
  }
}
