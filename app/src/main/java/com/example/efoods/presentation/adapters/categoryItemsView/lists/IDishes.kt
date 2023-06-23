package com.example.efoods.presentation.adapters.categoryItemsView.lists

import com.example.efoods.presentation.adapters.categoryItemsView.IDishView

interface IDishes<V: IDishView>  {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}