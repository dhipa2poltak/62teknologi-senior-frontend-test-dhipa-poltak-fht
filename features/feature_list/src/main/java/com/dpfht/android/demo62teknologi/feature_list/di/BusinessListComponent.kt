package com.dpfht.android.demo62teknologi.feature_list.di

import com.dpfht.android.demo62teknologi.feature_list.view.BusinessListFragment
import com.dpfht.android.demo62teknologi.framework.di.ApplicationComponent
import com.dpfht.android.demo62teknologi.framework.di.FragmentScope
import com.dpfht.android.demo62teknologi.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class, NavigationComponent::class], modules = [BusinessListModule::class])
@FragmentScope
interface BusinessListComponent {

  fun inject(businessListFragment: BusinessListFragment)
}
