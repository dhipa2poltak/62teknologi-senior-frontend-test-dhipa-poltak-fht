package com.dpfht.android.demo62teknologi.feature_details.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.dpfht.android.demo62teknologi.feature_details.databinding.PhotoItemBinding
import com.squareup.picasso.Picasso

class PhotoAdapter(
  private val context: Context,
  private val photos: List<String>
): PagerAdapter() {

  override fun getCount() = photos.size

  override fun isViewFromObject(view: View, `object`: Any) = view == `object`

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val binding = PhotoItemBinding.inflate(inflater, container, false)

    if (photos[position].isNotEmpty()) {
      Picasso.get().load(photos[position])
        .error(android.R.drawable.ic_menu_close_clear_cancel)
        //.placeholder(R.drawable.loading)
        .into(binding.photoImage)
    }

    container.addView(binding.root)

    return binding.root
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }
}
