package com.example.efoods.presentation.viewModel.categoryAppSatate

import com.example.efoods.domain.entity.Dishe
import com.example.efoods.domain.entity.MenuCategory
import com.example.efoods.presentation.adapters.categoryItemsView.ListDishes
import com.example.efoods.presentation.adapters.categoryItemsView.ListMenu

sealed class CategoryAppState {
    data class OnSuccess(
        val menuCategory: MenuCategory,
        val listMenu: ListMenu,
        val dishes: ArrayList<Dishe>,
        val listDishes: ListDishes
    ) : CategoryAppState()

    data class SetDishes(
        val menuCategory: MenuCategory,
        val listMenu: ListMenu,
        val dishes: ArrayList<Dishe>,
        val listDishes: ListDishes
    ) : CategoryAppState()


    data class Error(val error: String) : CategoryAppState()
    data class ShowDialogFragment(val dish: Dishe) : CategoryAppState()
}