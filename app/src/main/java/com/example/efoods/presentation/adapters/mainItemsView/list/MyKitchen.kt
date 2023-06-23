package com.example.efoods.presentation.adapters.mainItemsView.list

import com.example.efoods.domain.entity.СategoryKitchen
import com.example.efoods.presentation.adapters.mainItemsView.IMyKitchenView

class MyKitchen() : IMyListKitchen {
    override var itemClickListener: ((IMyKitchenView) -> Unit)? = null

    val categories : ArrayList<СategoryKitchen> = arrayListOf()

    override fun bindView(view: IMyKitchenView) {
        val category = categories[view.pos]
        view.setText(category.name)

            view.setImage(category.image_url)

    }

    override fun getCount(): Int = categories.size


}