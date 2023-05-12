package com.dpfht.android.demo62teknologi.feature_details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.android.demo62teknologi.domain.entity.ReviewEntity
import com.dpfht.android.demo62teknologi.feature_details.databinding.RowReviewBinding
import com.dpfht.android.demo62teknologi.feature_details.view.adapter.ReviewAdapter.ReviewViewHolder
import com.squareup.picasso.Picasso

class ReviewAdapter(
  private val reviews: ArrayList<ReviewEntity>
): RecyclerView.Adapter<ReviewViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
    val binding = RowReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    return ReviewViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
    val review = reviews[position]

    holder.bindData(review.text, review.user?.imageUrl ?: "", review.user?.name ?: "")
  }

  override fun getItemCount() = reviews.size

  class ReviewViewHolder(private val binding: RowReviewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(text: String, imageUrl: String, name: String) {
      binding.tvReview.text = text
      binding.tvUser.text = name

      if (imageUrl.isNotEmpty()) {
        Picasso.get().load(imageUrl)
          .error(android.R.drawable.ic_menu_close_clear_cancel)
          //.placeholder(R.drawable.loading)
          .into(binding.ivUser)
      }
    }
  }
}
