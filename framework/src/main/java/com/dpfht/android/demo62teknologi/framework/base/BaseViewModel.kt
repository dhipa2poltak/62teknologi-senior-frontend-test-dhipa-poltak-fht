package com.dpfht.android.demo62teknologi.framework.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dpfht.android.demo62teknologi.framework.navigation.NavigationService

abstract class BaseViewModel: ViewModel() {

  protected var mIsLoadingData = false

  protected val mIsShowDialogLoading = MutableLiveData<Boolean>()
  val isShowDialogLoading: LiveData<Boolean> = mIsShowDialogLoading

  protected val mErrorMessage = MutableLiveData<String>()
  val errorMessage: LiveData<String> = mErrorMessage

  private val mShowCanceledMessage = MutableLiveData<Boolean>()
  val showCanceledMessage: LiveData<Boolean> = mShowCanceledMessage

  abstract var navigationService: NavigationService

  fun isLoadingData() = mIsLoadingData

  fun showErrorMessage(message: String) {
    navigationService.navigateToErrorMessage(message)
  }

  fun showCanceledMessage() {
    //showErrorMessage())
  }

  fun resetError() {
    mErrorMessage.postValue("")
  }
}
