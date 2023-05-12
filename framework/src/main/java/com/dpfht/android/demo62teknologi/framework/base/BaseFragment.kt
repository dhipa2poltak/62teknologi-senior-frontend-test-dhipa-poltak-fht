package com.dpfht.android.demo62teknologi.framework.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<T: BaseViewModel>: Fragment() {

  abstract val viewModel: T

  open fun observeViewModel() {
    viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
      if (message.isNotEmpty()) {
        viewModel.showErrorMessage(message)
        viewModel.resetError()
      }
    }

    viewModel.showCanceledMessage.observe(viewLifecycleOwner) { isShow ->
      if (isShow) {
        viewModel.showCanceledMessage()
      }
    }
  }
}
