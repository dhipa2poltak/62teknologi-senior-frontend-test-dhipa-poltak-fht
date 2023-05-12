package com.dpfht.android.demo62teknologi.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.dpfht.android.demo62teknologi.R
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService

class NavigationServiceImpl(private val navController: NavController): NavigationService {

  override fun navigateToBusinessList() {
    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
    navGraph.setStartDestination(R.id.business_list_fragment)

    navController.graph = navGraph
  }

  override fun navigateToBusinessDetails(businessId: String) {
    val builder = Uri.Builder()
    builder.scheme("android-app")
      .authority("demo62teknologi.dpfht.com")
      .appendPath("business_details_fragment")
      .appendQueryParameter("business_id", businessId)

    navController.navigate(NavDeepLinkRequest.Builder
      .fromUri(builder.build())
      .build())
  }

  override fun navigateToErrorMessage(message: String) {
    val builder = Uri.Builder()
    builder.scheme("android-app")
      .authority("demo62teknologi.dpfht.com")
      .appendPath("error_message_dialog_fragment")
      .appendQueryParameter("message", message)

    navController.navigate(
      NavDeepLinkRequest.Builder
      .fromUri(builder.build())
      .build())
  }
}
