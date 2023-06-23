package com.example.efoods.presentation.adapters.categoryItemsView.lists

import android.view.View
import com.example.efoods.presentation.adapters.categoryItemsView.IMenuView

interface IMenu<V: IMenuView> {
    var itemClickListener: ((V) -> Unit)?
    var itemClickListenerMenu: ((View) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}