package com.dpfht.android.demo62teknologi.feature_list.di

import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.repository.AppRepository
import com.dpfht.android.demo62teknologi.domain.usecase.SearchBusinessUseCase
import com.dpfht.android.demo62teknologi.domain.usecase.SearchBusinessUseCaseImpl
import com.dpfht.android.demo62teknologi.feature_list.view.BusinessListViewModel
import com.dpfht.android.demo62teknologi.feature_list.view.adapter.BusinessListAdapter
import com.dpfht.android.demo62teknologi.framework.di.FragmentScope
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides

@Module
class BusinessListModule {

  @Provides
  @FragmentScope
  fun provideBusinesses(): ArrayList<BusinessEntity> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideSearchBusinessUseCase(appRepository: AppRepository): SearchBusinessUseCase {
    return SearchBusinessUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideBusinessListViewModel(
    businesses: ArrayList<BusinessEntity>,
    searchBusinessUseCase: SearchBusinessUseCase,
    navigationService: NavigationService
  ): BusinessListViewModel {
    return BusinessListViewModel(businesses, searchBusinessUseCase, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideBusinessListAdapter(businesses: ArrayList<BusinessEntity>): BusinessListAdapter {
    return BusinessListAdapter(businesses)
  }
}
