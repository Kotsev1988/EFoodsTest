package com.example.efoods.presentation.viewModel.mainAppState

import com.example.efoods.domain.entity.СategoryKitchen
import com.example.efoods.presentation.adapters.mainItemsView.list.IMyListKitchen

sealed class AppState{
    data class OnSuccess(

        val myCardProducts: List<СategoryKitchen>,
        val productsListPresenter: IMyListKitchen,

        ) : AppState()

    data class Error(val error: String) : AppState()
}
