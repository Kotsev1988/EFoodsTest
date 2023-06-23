package com.example.efoods.presentation.viewModel.categoryAppSatate

import com.example.efoods.domain.entity.Dishe

sealed class DetailAppState{

    data class OnSuccess(
        val dishe: Dishe
    ) : DetailAppState()

    data class IsContain(
        val isContain: Boolean
    ) : DetailAppState()

    data class AddToCard(
        val isAdded: Boolean
    ) : DetailAppState()

    data class Error(val error: String) : DetailAppState()
}
