package com.example.efoods.presentation.adapters.mainItemsView.list

import com.example.efoods.presentation.adapters.mainItemsView.IMyKitchenView

interface IListKitchen<V: IMyKitchenView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}