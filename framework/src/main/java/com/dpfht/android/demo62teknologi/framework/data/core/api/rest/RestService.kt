package com.dpfht.android.demo62teknologi.framework.data.core.api.rest

import com.dpfht.android.demo62teknologi.data.model.Business
import com.dpfht.android.demo62teknologi.data.model.response.ReviewsResponse
import com.dpfht.android.demo62teknologi.data.model.response.SearchBusinessResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {

  @GET("v3/businesses/search")
  suspend fun searchBusiness(
    @Query("location") location: String,
    @Query("term") term: String,
    @Query("price") prices: List<Int>,
    @Query("sort_by") sortBy: String,
    @Query("offset") offset: Int,
    @Query("limit") limit: Int
  ): SearchBusinessResponse

  @GET("v3/businesses/{business_id}")
  suspend fun getBusinessById(
    @Path("business_id") businessId: String
  ): Business

  @GET("v3/businesses/{business_id}/reviews")
  suspend fun getBusinessReviews(
    @Path("business_id") businessId: String,
    @Query("sort_by") sortBy: String,
    @Query("offset") offset: Int,
    @Query("limit") limit: Int
  ): ReviewsResponse
}
