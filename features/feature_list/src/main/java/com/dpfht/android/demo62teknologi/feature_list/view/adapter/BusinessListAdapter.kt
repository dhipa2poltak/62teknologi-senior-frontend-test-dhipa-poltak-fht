package com.dpfht.android.demo62teknologi.feature_list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.android.demo62teknologi.domain.entity.BusinessEntity
import com.dpfht.android.demo62teknologi.feature_list.databinding.RowBusinessBinding
import com.dpfht.android.demo62teknologi.feature_list.view.adapter.BusinessListAdapter.BusinessListViewHolder
import com.squareup.picasso.Picasso

class BusinessListAdapter(
  private val businesses: ArrayList<BusinessEntity>
): RecyclerView.Adapter<BusinessListViewHolder>() {

  var onClickBusinessListener: OnClickBusinessListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessListViewHolder {
    val binding = RowBusinessBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    return BusinessListViewHolder(binding)
  }

  override fun onBindViewHolder(holder: BusinessListViewHolder, position: Int) {
    val business = businesses[position]

    holder.bindData(business.imageUrl, business.name, business.price)
    holder.itemView.setOnClickListener {
      onClickBusinessListener?.onClickBusiness(position)
    }
  }

  override fun getItemCount() = businesses.size

  class BusinessListViewHolder(private val binding: RowBusinessBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(imageUrl: String, titleBusiness: String, price: String) {
      if (imageUrl.isNotEmpty()) {
        Picasso.get().load(imageUrl)
          .error(android.R.drawable.ic_menu_close_clear_cancel)
          //.placeholder(R.drawable.loading)
          .into(binding.ivBusiness)
      }

      binding.tvTitleBusiness.text = titleBusiness
      binding.tvOverviewBusiness.text = price
    }
  }

  interface OnClickBusinessListener {
    fun onClickBusiness(position: Int)
  }
}
