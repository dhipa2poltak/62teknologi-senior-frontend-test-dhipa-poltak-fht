package com.dpfht.android.demo62teknologi.feature_details.di

import com.dpfht.android.demo62teknologi.feature_details.view.BusinessDetailsFragment
import com.dpfht.android.demo62teknologi.framework.di.ApplicationComponent
import com.dpfht.android.demo62teknologi.framework.di.FragmentScope
import com.dpfht.android.demo62teknologi.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class, NavigationComponent::class], modules = [BusinessDetailsModule::class])
@FragmentScope
interface BusinessDetailsComponent {

  fun inject(businessDetailsFragment: BusinessDetailsFragment)
}
