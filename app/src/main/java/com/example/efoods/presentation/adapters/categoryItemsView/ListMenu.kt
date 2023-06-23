package com.example.efoods.presentation.adapters.categoryItemsView

import android.view.View
import com.example.efoods.domain.entity.MenuCategory
import com.example.efoods.presentation.adapters.categoryItemsView.lists.IListMenu

class ListMenu(): IListMenu {
    override var itemClickListenerMenu: ((View) -> Unit)? = null
    override var itemClickListener: ((IMenuItemView) -> Unit)? = null

    val menuCategory = MenuCategory()

    override fun bindView(view: IMenuItemView) {

        var menu = menuCategory[view.pos]

        view.setText(menu.name)
        view.clickButton()

        if (menu.select ){
            view.changeColor(0)
        }else{
            view.changeColor(1)
        }
    }

    override fun getCount(): Int = menuCategory.size
}