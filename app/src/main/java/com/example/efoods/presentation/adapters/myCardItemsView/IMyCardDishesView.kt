package com.example.efoods.presentation.adapters.myCardItemsView

interface IMyCardDishesView : IMyDishesView {
    fun setText(text: String)
    fun setPrice(price: String)
    fun setWeght(weight: String)
    fun loadAvatar(url: String)
    fun setCounter(counter: String)
}