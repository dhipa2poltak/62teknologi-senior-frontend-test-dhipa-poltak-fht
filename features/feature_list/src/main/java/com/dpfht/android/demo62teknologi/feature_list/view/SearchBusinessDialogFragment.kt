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
  private var prices = arrayListOf(false, false, false, false)

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

    binding.cb1.isChecked = prices[0]
    binding.cb2.isChecked = prices[1]
    binding.cb3.isChecked = prices[2]
    binding.cb4.isChecked = prices[3]

    binding.cb1.setOnCheckedChangeListener { _, isChecked ->
      prices[0] = isChecked
    }

    binding.cb2.setOnCheckedChangeListener { _, isChecked ->
      prices[1] = isChecked
    }

    binding.cb3.setOnCheckedChangeListener { _, isChecked ->
      prices[2] = isChecked
    }

    binding.cb4.setOnCheckedChangeListener { _, isChecked ->
      prices[3] = isChecked
    }

    binding.btnCancel.setOnClickListener {
      dismiss()
    }

    binding.btnSearch.setOnClickListener {
      if (isValidInput()) {
        location = binding.etLocation.text.toString().trim()
        term = binding.etTerm.text.toString().trim()

        dismiss()
        onClickSearchCallback?.onClickSearch(location, term, prices)
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
    fun onClickSearch(location: String, term: String, prices: ArrayList<Boolean>)
  }

  companion object {
    fun newInstance(location: String, term: String, prices: ArrayList<Boolean>): SearchBusinessDialogFragment {
      val fragment = SearchBusinessDialogFragment()
      fragment.location = location
      fragment.term = term
      fragment.prices = prices

      return fragment
    }
  }
}
