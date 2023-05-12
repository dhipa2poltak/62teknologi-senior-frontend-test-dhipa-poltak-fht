package com.dpfht.android.demo62teknologi.feature_list.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.android.demo62teknologi.feature_list.R
import com.dpfht.android.demo62teknologi.feature_list.databinding.FragmentBusinessListBinding
import com.dpfht.android.demo62teknologi.feature_list.di.BusinessListModule
import com.dpfht.android.demo62teknologi.feature_list.di.DaggerBusinessListComponent
import com.dpfht.android.demo62teknologi.feature_list.view.SearchBusinessDialogFragment.OnClickSearchCallback
import com.dpfht.android.demo62teknologi.feature_list.view.adapter.BusinessListAdapter
import com.dpfht.android.demo62teknologi.framework.base.BaseFragment
import com.dpfht.android.demo62teknologi.framework.di.provider.ApplicationComponentProvider
import com.dpfht.android.demo62teknologi.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject

class BusinessListFragment: BaseFragment<BusinessListViewModel>() {

  private lateinit var binding: FragmentBusinessListBinding

  @Inject
  override lateinit var viewModel: BusinessListViewModel

  @Inject
  lateinit var adapter: BusinessListAdapter

  private var vw: View? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)

    DaggerBusinessListComponent
      .builder()
      .businessListModule(BusinessListModule())
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent((requireActivity().applicationContext as ApplicationComponentProvider).provideApplicationComponent())
      .build()
      .inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    return if (vw != null) {
      vw
    } else {
      binding = FragmentBusinessListBinding.inflate(inflater, container, false)
      vw = binding.root
      vw
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setToolbar()
    setHasOptionsMenu(true)

    val layoutManager = LinearLayoutManager(requireContext())
    layoutManager.orientation = LinearLayoutManager.VERTICAL

    binding.rvBusiness.layoutManager = layoutManager
    binding.rvBusiness.adapter = adapter

    val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
    binding.rvBusiness.addItemDecoration(dividerItemDecoration)

    adapter.onClickBusinessListener = object : BusinessListAdapter.OnClickBusinessListener {
      override fun onClickBusiness(position: Int) {
        viewModel.navigateToBusinessDetails(position)
      }
    }

    binding.rvBusiness.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val xx = recyclerView.computeVerticalScrollRange()
        val xy = recyclerView.computeVerticalScrollOffset()
        val xz = recyclerView.computeVerticalScrollExtent()
        val zz = (xy.toFloat() / (xx - xz).toFloat() * 100).toInt()
        if (zz >= 75 && !viewModel.isLoadingData()) {
          viewModel.loadMoreBusinessData()
        }
        super.onScrolled(recyclerView, dx, dy)
      }
    })

    observeViewModel()

    if (viewModel.businesses.isEmpty()) {
      viewModel.newSearchBusiness("NYC", "food", arrayListOf(false, false, false, false))
      showDescResult("NYC", "food", arrayListOf(false, false, false, false))
    }
  }

  override fun observeViewModel() {
    super.observeViewModel()

    viewModel.notifyClearDataView.observe(viewLifecycleOwner) { isClearing ->
      if (isClearing) {
        adapter.notifyDataSetChanged()
        viewModel.resetNotifyClearData()
      }
    }

    viewModel.notifyNoBusinessData.observe(viewLifecycleOwner) { isNoData ->
      binding.tvNoData.visibility = if (isNoData) {
        View.VISIBLE
      } else {
        View.GONE
      }
    }

    viewModel.isShowDialogLoading.observe(viewLifecycleOwner) { value ->
      binding.pbLoading.visibility = if (value) {
        View.VISIBLE
      } else {
        View.GONE
      }
    }

    viewModel.notifyItemInserted.observe(viewLifecycleOwner) { position ->
      if (position > 0) {
        adapter.notifyItemInserted(position)
      }
    }
  }

  private fun setToolbar() {
    (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)

    inflater.inflate(R.menu.menu_list, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.itm_search -> {
        val fragment = SearchBusinessDialogFragment.newInstance(viewModel.location, viewModel.term, viewModel.prices)
        fragment.onClickSearchCallback = object : OnClickSearchCallback {
          override fun onClickSearch(location: String, term: String, prices: ArrayList<Boolean>) {
            showDescResult(location, term, prices)
            viewModel.newSearchBusiness(location, term, prices)
          }
        }
        fragment.show(requireActivity().supportFragmentManager, "search_business_dialog_fragment")

        return true
      }
    }

    return super.onOptionsItemSelected(item)
  }

  private fun showDescResult(location: String, term: String, prices: ArrayList<Boolean>) {
    var msg = "showing result for\nlocation: $location"
    if (term.isNotEmpty()) {
      msg += ", term: $term"
    }

    var textPrices = ""
    for (i in 0 until prices.size) {
      if (prices[i]) {
        if (textPrices.isEmpty()) {
          textPrices = "${i + 1}"
        } else {
          textPrices += ", ${i + 1}"
        }
      }
    }

    if (textPrices.isNotEmpty()) {
      msg += ", price(s): ($textPrices)"
    }

    binding.tvDesc.text = msg
  }
}
