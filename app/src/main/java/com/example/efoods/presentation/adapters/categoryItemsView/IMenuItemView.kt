package com.example.efoods.presentation.adapters.categoryItemsView

interface IMenuItemView: IMenuView {
    fun clickButton()
    fun setText(text: String)
    fun changeColor(pos: Int)
}