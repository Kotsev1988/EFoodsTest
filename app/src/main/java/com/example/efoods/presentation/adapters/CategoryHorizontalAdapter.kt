package com.example.efoods.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.efoods.databinding.ItemMenuNameBinding
import com.example.efoods.presentation.adapters.categoryItemsView.IMenuItemView
import com.example.efoods.presentation.adapters.categoryItemsView.lists.IListMenu

class CategoryHorizontalAdapter(private val categoryList: IListMenu): RecyclerView.Adapter<CategoryHorizontalAdapter.CategoryHorizontalViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoryHorizontalViewHolder = CategoryHorizontalViewHolder(
        ItemMenuNameBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)).apply {
            categoryList.itemClickListener?.invoke(this)
    }

    override fun getItemCount(): Int = categoryList.getCount()

    override fun onBindViewHolder(holder: CategoryHorizontalViewHolder, position: Int) {
        categoryList.bindView(holder.apply {
            pos = position
        })
    }

    inner class CategoryHorizontalViewHolder(private val binding: ItemMenuNameBinding):RecyclerView.ViewHolder(binding.root),
        IMenuItemView {

        override var pos: Int=0

        override fun clickButton() {
            binding.categoryMenuName.setOnClickListener {
                categoryList.itemClickListener?.invoke(this)
                notifyDataSetChanged()
            }
        }

        override fun setText(text: String) {
            binding.categoryMenuName.text = text
        }

        override fun changeColor(pos: Int) {
            if (pos == 0){
                binding.categoryMenuName.isSelected = true
            }else{
                binding.categoryMenuName.isSelected = false
            }
        }


    }
}