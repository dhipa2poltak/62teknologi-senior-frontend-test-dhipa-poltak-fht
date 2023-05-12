package com.dpfht.android.demo62teknologi.feature_list.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.domain.entity.Result.ErrorResult
import com.dpfht.android.demo62teknologi.domain.entity.Result.Success
import com.dpfht.android.demo62teknologi.domain.usecase.SearchBusinessUseCase
import com.dpfht.android.demo62teknologi.framework.Constants
import com.dpfht.android.demo62teknologi.framework.base.BaseViewModel
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService
import kotlinx.coroutines.launch

class BusinessListViewModel(
  val businesses: ArrayList<BusinessEntity>,
  private val searchBusinessUseCase: SearchBusinessUseCase,
  override var navigationService: NavigationService
): BaseViewModel() {

  var location = ""
  var term = ""
  var prices = arrayListOf(false, false, false, false)

  private val _notifyClearDataView = MutableLiveData<Boolean>()
  val notifyClearDataView: LiveData<Boolean> = _notifyClearDataView

  private val _notifyNoBusinessData = MutableLiveData<Boolean>()
  val notifyNoBusinessData: LiveData<Boolean> = _notifyNoBusinessData

  private val _notifyItemInserted = MutableLiveData<Int>()
  val notifyItemInserted: LiveData<Int> = _notifyItemInserted

  fun newSearchBusiness(location: String, term: String, prices: ArrayList<Boolean>) {
    businesses.clear()
    _notifyClearDataView.postValue(true)
    _notifyNoBusinessData.postValue(true)

    this.location = location
    this.term = term
    this.prices = prices

    searchBusiness(location, term, prices)
  }

  private fun searchBusiness(location: String, term: String, prices: ArrayList<Boolean>) {

    mIsShowDialogLoading.postValue(true)
    mIsLoadingData = true

    viewModelScope.launch {
      val arrPrice = arrayListOf<Int>()
      for (i in 0 until prices.size) {
        if (prices[i]) {
          arrPrice.add(i + 1)
        }
      }

      when (val result = searchBusinessUseCase(location, term, arrPrice.toList(), "best_match", businesses.size, Constants.PAGING_LIMIT)) {
        is Success -> {
          onSuccess(result.value.businesses)
        }
        is ErrorResult -> {
          onError(result.message)
        }
      }
    }
  }

  fun loadMoreBusinessData() {
    searchBusiness(location, term, prices)
  }

  private fun onSuccess(businesses: List<BusinessEntity>) {
    for (business in businesses) {
      this.businesses.add(business)
      _notifyItemInserted.postValue(this.businesses.size - 1)
    }
    mIsShowDialogLoading.postValue(false)
    mIsLoadingData = false

    _notifyNoBusinessData.postValue(this.businesses.isEmpty())
  }

  private fun onError(message: String) {
    mIsShowDialogLoading.postValue(false)
    mIsLoadingData = false
    mErrorMessage.postValue(message)
  }

  fun navigateToBusinessDetails(position: Int) {
    navigationService.navigateToBusinessDetails(businesses[position].id)
  }

  fun resetNotifyClearData() {
    _notifyClearDataView.postValue(false)
  }
}
