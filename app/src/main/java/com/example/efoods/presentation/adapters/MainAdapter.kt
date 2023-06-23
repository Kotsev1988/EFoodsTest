package com.example.efoods.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.efoods.databinding.KitchenItemBinding
import com.example.efoods.presentation.adapters.mainItemsView.IMyKitchenView
import com.example.efoods.presentation.adapters.mainItemsView.list.IMyListKitchen
import javax.inject.Inject

class MainAdapter(var myKitchen: IMyListKitchen) :
    RecyclerView.Adapter<MainAdapter.KitchenViewHolder>() {

    @Inject
    lateinit var imageLoader: com.example.efoods.domain.image.ILoadImage<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitchenViewHolder {
        return KitchenViewHolder(
            KitchenItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                myKitchen.itemClickListener?.invoke(this)
            }
        }
    }

    override fun getItemCount(): Int = myKitchen.getCount()

    override fun onBindViewHolder(holder: KitchenViewHolder, position: Int) {
        myKitchen.bindView(holder.apply {
            pos = position
        })
    }

    inner class KitchenViewHolder(private val binding: KitchenItemBinding) :
        RecyclerView.ViewHolder(binding.root), IMyKitchenView {
        override fun setText(text: String) {
            binding.frameKitschen.setOnClickListener {
                myKitchen.itemClickListener?.invoke(this)
            }
            binding.kitchenName.text = text
        }

        override fun setImage(url: String) {

            imageLoader.loadImage(url, binding.kitchenImage)
            binding.kitchenImage.setOnClickListener {
                myKitchen.itemClickListener?.invoke(this)
            }
        }

        override var pos: Int = -1
    }

}
