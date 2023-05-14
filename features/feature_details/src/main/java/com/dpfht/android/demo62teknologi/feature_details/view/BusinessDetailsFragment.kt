package com.dpfht.android.demo62teknologi.feature_details.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dpfht.android.demo62teknologi.feature_details.databinding.FragmentBusinessDetailsBinding
import com.dpfht.android.demo62teknologi.feature_details.di.BusinessDetailsModule
import com.dpfht.android.demo62teknologi.feature_details.di.DaggerBusinessDetailsComponent
import com.dpfht.android.demo62teknologi.feature_details.view.adapter.PhotoAdapter
import com.dpfht.android.demo62teknologi.feature_details.view.adapter.ReviewAdapter
import com.dpfht.android.demo62teknologi.framework.base.BaseFragment
import com.dpfht.android.demo62teknologi.framework.di.provider.ApplicationComponentProvider
import com.dpfht.android.demo62teknologi.framework.di.provider.NavigationComponentProvider
import java.lang.Exception
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

class BusinessDetailsFragment: BaseFragment<BusinessDetailsViewModel>() {

  private lateinit var binding: FragmentBusinessDetailsBinding

  @Inject
  override lateinit var viewModel: BusinessDetailsViewModel

  @Inject
  lateinit var adapter: ReviewAdapter

  private var vw: View? = null

  private var timer: Timer? = null
  private var taskSlide: TaskSlide? = null
  private val handler = Handler(Looper.getMainLooper())

  override fun onAttach(context: Context) {
    super.onAttach(context)

    DaggerBusinessDetailsComponent
      .builder()
      .businessDetailsModule(BusinessDetailsModule())
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
      binding = FragmentBusinessDetailsBinding.inflate(inflater, container, false)
      vw = binding.root
      vw
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val layoutManager = LinearLayoutManager(requireContext())
    layoutManager.orientation = LinearLayoutManager.VERTICAL

    binding.rvReviews.layoutManager = layoutManager
    binding.rvReviews.adapter = adapter

    observeViewModel()

    arguments?.let {
      val businessId = it.getString("business_id")

      if (businessId != null && businessId.isNotEmpty()) {
        viewModel.loadBusinessData(businessId)
      }
    }
  }

  override fun observeViewModel() {
    super.observeViewModel()

    viewModel.isShowDialogLoading.observe(viewLifecycleOwner) { value ->
      binding.pbLoading.visibility = if (value) {
        View.VISIBLE
      } else {
        View.GONE
      }
    }

    viewModel.notifyNoReviewData.observe(viewLifecycleOwner) { isNoData ->
      binding.tvNoData.visibility = if (isNoData) {
        View.VISIBLE
      } else {
        View.GONE
      }
    }

    viewModel.notifyTitleBusiness.observe(viewLifecycleOwner) { title ->
      binding.tvTitle.text = title
    }

    viewModel.notifyPhotos.observe(viewLifecycleOwner) { photos ->
      if (photos.isNotEmpty()) {
        val photoAdapter = PhotoAdapter(requireContext(), photos)
        binding.myPager.adapter = photoAdapter
        binding.myTablayout.setupWithViewPager(binding.myPager, true)

        taskSlide?.cancel()
        timer?.cancel()

        if (photos.size > 1) {
          taskSlide = TaskSlide(binding.myPager, photos, handler)
          timer = Timer()
          timer?.scheduleAtFixedRate(taskSlide, 2000, 3000)
        }
      }
    }

    viewModel.notifyRating.observe(viewLifecycleOwner) { rating ->
      binding.rbBusiness.rating = rating.toFloat()
      binding.tvRating.text = rating.toString()
    }

    viewModel.notifyLocation.observe(viewLifecycleOwner) { pair ->
      if (pair.first != 0.0 && pair.second != 0.0) {
        binding.tvShowLocationOnMap.visibility = View.VISIBLE
        binding.tvShowLocationOnMap.setOnClickListener {
          openMap(pair.first, pair.second)
        }
      } else {
        binding.tvShowLocationOnMap.visibility = View.GONE
      }
    }

    viewModel.notifyItemInserted.observe(viewLifecycleOwner) { position ->
      if (position > 0) {
        adapter.notifyItemInserted(position)
      }
    }
  }

  private fun openMap(latitude: Double, longitude: Double) {
    val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,%20$longitude")

    try {
      val intent = Intent(Intent.ACTION_VIEW, uri)
      requireContext().startActivity(intent)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun onDestroy() {
    super.onDestroy()

    taskSlide?.cancel()
    timer?.cancel()

    taskSlide = null
    timer = null
  }

  class TaskSlide(
    private val pager: ViewPager,
    private val photos: List<String>,
    private val handler: Handler
  ): TimerTask() {

    override fun run() {
      handler.post {
        pager.currentItem = if (pager.currentItem < photos.size - 1) {
          pager.currentItem + 1
        } else {
          0
        }
      }
    }
  }
}
