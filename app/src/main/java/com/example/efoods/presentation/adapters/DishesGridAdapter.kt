package com.example.efoods.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.efoods.R
import com.example.efoods.databinding.ItemFoodBinding
import com.example.efoods.presentation.adapters.categoryItemsView.IDishItemView
import com.example.efoods.presentation.adapters.categoryItemsView.ListDishes
import com.example.efoods.presentation.adapters.categoryItemsView.lists.IDishesList

class DishesGridAdapter(private var dishesList: IDishesList) :
    RecyclerView.Adapter<DishesGridAdapter.DishesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DishesViewHolder = DishesViewHolder(
        ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
        .apply {
            itemView.setOnClickListener {
                dishesList.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        dishesList.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int = dishesList.getCount()
    fun setData(listDishes: ListDishes) {

        dishesList = listDishes
        notifyDataSetChanged()
    }

    inner class DishesViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root),
        IDishItemView {
        override fun setText(text: String) {
            binding.foodName.text = text
        }
        override fun setDescription(text: String) {
            binding.description.text = text
        }

        override fun setPrice(text: String) {
            binding.price.text = "от "+text+" р"
        }
        override fun setImage(url: String) {

            if (url == "image"){
                Glide.with(binding.foodImage.context).load(R.drawable.ic_image_not_found).diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.foodImage)
            }else{
                Glide.with(binding.foodImage.context).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.foodImage)
            }


        }

        override var pos: Int = -1

    }
}