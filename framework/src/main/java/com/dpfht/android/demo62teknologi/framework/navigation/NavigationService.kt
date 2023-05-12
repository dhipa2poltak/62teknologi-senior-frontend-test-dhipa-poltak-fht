package com.dpfht.android.demo62teknologi.framework.navigation

interface NavigationService {

  fun navigateToBusinessList()
  fun navigateToBusinessDetails(businessId: String)
  fun navigateToErrorMessage(message: String)
}
