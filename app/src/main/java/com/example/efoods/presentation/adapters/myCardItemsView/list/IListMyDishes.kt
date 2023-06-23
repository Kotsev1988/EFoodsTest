package com.example.efoods.presentation.adapters.myCardItemsView.list

import com.example.efoods.presentation.adapters.myCardItemsView.IMyCardDishesView

interface IListMyDishes<V: IMyCardDishesView> {

    var itemClickListenerIncrease: ((V) -> Unit)?
    var itemClickListenerDecrease: ((V) -> Unit)?
    var itemClickListenerDelete: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}