package com.example.efoods.presentation.adapters.categoryItemsView

interface IDishItemView: IDishView {
    fun setText(text: String)
    fun setDescription(text: String)
    fun setPrice(text: String)
    fun setImage(url: String)

}