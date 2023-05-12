package com.dpfht.android.demo62teknologi.feature_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.fragment.app.DialogFragment
import com.dpfht.android.demo62teknologi.framework.R
import com.dpfht.android.demo62teknologi.feature_list.databinding.FragmentDialogSearchBusinessBinding

class SearchBusinessDialogFragment: DialogFragment() {

  private lateinit var binding: FragmentDialogSearchBusinessBinding

  var onClickSearchCallback: OnClickSearchCallback? = null

  private var location = ""
  private var term = ""

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentDialogSearchBusinessBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.etLocation.setText(location)
    binding.etTerm.setText(term)

    binding.btnCancel.setOnClickListener {
      dismiss()
    }

    binding.btnSearch.setOnClickListener {
      if (isValidInput()) {
        location = binding.etLocation.text.toString().trim()
        term = binding.etTerm.text.toString().trim()

        dismiss()
        onClickSearchCallback?.onClickSearch(location, term)
      }
    }
  }

  private fun isValidInput(): Boolean {
    if (binding.etLocation.text.toString().trim().isEmpty()) {
      binding.etLocation.error = getString(R.string.text_error_location)
      return false
    }

    return true
  }

  override fun onResume() {
    super.onResume()

    val params = dialog?.window?.attributes
    if (params != null) {
      params.width = LayoutParams.MATCH_PARENT
      params.height = LayoutParams.WRAP_CONTENT
      dialog?.window?.attributes = params
    }
  }

  interface OnClickSearchCallback {
    fun onClickSearch(location: String, term: String)
  }

  companion object {
    fun newInstance(location: String, term: String): SearchBusinessDialogFragment {
      val fragment = SearchBusinessDialogFragment()
      fragment.location = location
      fragment.term = term

      return fragment
    }
  }
}
