package com.example.efoods.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.efoods.R
import com.example.efoods.databinding.BannersItemBinding

class BannersHorizontalAdapter(): RecyclerView.Adapter<BannersHorizontalAdapter.BannerHorizontalViewHolder>() {

    var bannerList = mutableListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BannerHorizontalViewHolder = BannerHorizontalViewHolder(
        BannersItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    fun setData(newBanners: ArrayList<String>){
        this.bannerList = newBanners
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: BannerHorizontalViewHolder, position: Int) {
     holder.bind(bannerList[position])
    }

    inner class BannerHorizontalViewHolder(private val binding: BannersItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String) {

            binding.bannersImage.setImageResource(R.drawable.dish_banner)
        }
    }
}