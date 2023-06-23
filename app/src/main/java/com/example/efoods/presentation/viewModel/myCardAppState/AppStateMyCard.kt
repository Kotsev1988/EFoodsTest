package com.example.efoods.presentation.viewModel.myCardAppState

import com.example.efoods.domain.entity.Dishe
import com.example.efoods.presentation.adapters.myCardItemsView.list.IListMyCardDishesView

sealed class AppStateMyCard{
    data class OnSuccess(

        val myCardProducts: List<Dishe>,
        val myCardList: IListMyCardDishesView,

        ) : AppStateMyCard()

    data class OnUpdate(

        val myCardList: IListMyCardDishesView,

        ) : AppStateMyCard()

    data class TotalPrice(

        val totalPrice: String,

        ) : AppStateMyCard()

    data class Error(val error: String) : AppStateMyCard()
}
