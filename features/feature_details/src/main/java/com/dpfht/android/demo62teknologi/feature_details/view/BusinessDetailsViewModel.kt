package com.dpfht.android.demo62teknologi.feature_details.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.Result.ErrorResult
import com.dpfht.android.demo62teknologi.domain.entity.Result.Success
import com.dpfht.android.demo62teknologi.domain.entity.ReviewEntity
import com.dpfht.android.demo62teknologi.domain.usecase.GetBusinessByIdUseCase
import com.dpfht.android.demo62teknologi.domain.usecase.GetBusinessReviewsUseCase
import com.dpfht.android.demo62teknologi.framework.Constants
import com.dpfht.android.demo62teknologi.framework.base.BaseViewModel
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import kotlinx.coroutines.launch

class BusinessDetailsViewModel(
  private val reviews: ArrayList<ReviewEntity>,
  private val getBusinessByIdUseCase: GetBusinessByIdUseCase,
  private val getBusinessReviewsUseCase: GetBusinessReviewsUseCase,
  override var navigationService: NavigationService
): BaseViewModel() {

  private var businessId = ""

  private val _notifyTitleBusiness = MutableLiveData<String>()
  val notifyTitleBusiness: LiveData<String> = _notifyTitleBusiness

  private val _notifyPhotos = MutableLiveData<List<String>>()
  val notifyPhotos: LiveData<List<String>> = _notifyPhotos

  private val _notifyRating = MutableLiveData<Double>()
  val notifyRating: LiveData<Double> = _notifyRating

  private val _notifyItemInserted = MutableLiveData<Int>()
  val notifyItemInserted: LiveData<Int> = _notifyItemInserted

  private val _notifyLocation = MutableLiveData<Pair<Double, Double>>()
  val notifyLocation: LiveData<Pair<Double, Double>> = _notifyLocation

  private val _notifyNoReviewData = MutableLiveData<Boolean>()
  val notifyNoReviewData: LiveData<Boolean> = _notifyNoReviewData

  fun loadBusinessData(businessId: String) {

    mIsShowDialogLoading.postValue(true)
    mIsLoadingData = true

    viewModelScope.launch {
      when (val result = getBusinessByIdUseCase(businessId)) {
        is Success -> {
          this@BusinessDetailsViewModel.businessId = businessId

          onSuccessLoadBusiness(result.value)
        }
        is ErrorResult -> {
          onErrorLoadBusiness(result.message)
        }
      }
    }

    newLoadReviews(businessId)
  }

  private fun onSuccessLoadBusiness(business: BusinessEntity) {
    _notifyTitleBusiness.postValue(business.name)
    _notifyPhotos.postValue(business.photos)
    _notifyRating.postValue(business.rating)
    _notifyLocation.postValue(Pair(business.coordinates?.latitude ?: 0.0, business.coordinates?.longitude ?: 0.0))
  }

  private fun onErrorLoadBusiness(message: String) {
    mErrorMessage.postValue(message)
  }

  private fun newLoadReviews(businessId: String) {
    _notifyNoReviewData.postValue(true)

    this.businessId = businessId

    loadReviews(businessId)
  }

  private fun loadReviews(businessId: String) {
    viewModelScope.launch {
      when (val result = getBusinessReviewsUseCase(businessId, "newest", reviews.size, Constants.PAGING_LIMIT)) {
        is Success -> {
          onSuccessLoadReviews(result.value.reviews)
        }
        is ErrorResult -> {
          onErrorLoadReviews(result.message)
        }
      }
    }
  }

  fun loadMoreReviews() {
    loadReviews(businessId)
  }

  private fun onSuccessLoadReviews(reviews: List<ReviewEntity>) {
    for (review in reviews) {
      this.reviews.add(review)
      _notifyItemInserted.postValue(this.reviews.size - 1)
    }

    mIsShowDialogLoading.postValue(false)
    mIsLoadingData = false

    _notifyNoReviewData.postValue(this.reviews.isEmpty())
  }

  private fun onErrorLoadReviews(message: String) {
    mIsShowDialogLoading.postValue(false)
    mIsLoadingData = false
    mErrorMessage.postValue(message)
  }
}
