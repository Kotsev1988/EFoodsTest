package com.example.efoods.presentation.adapters.categoryItemsView

import androidx.core.content.ContextCompat
import com.example.efoods.R
import com.example.efoods.domain.entity.Dishe
import com.example.efoods.presentation.adapters.categoryItemsView.lists.IDishesList

class ListDishes(): IDishesList {
    override var itemClickListener: ((IDishItemView) -> Unit)? = null

    var dishes: ArrayList<Dishe> = arrayListOf()

    override fun bindView(view: IDishItemView) {

        val dish = dishes[view.pos]

        view.setText(dish.name)

        view.setDescription(dish.description)

        view.setPrice(dish.price.toString())


        if (dishes[view.pos].image_url!=null) {
           view.setImage(dish.image_url)
       }else{
            view.setImage("image")
        }


    }

    override fun getCount(): Int = dishes.size
}