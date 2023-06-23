package com.example.efoods.domain.entity.repository

import com.example.efoods.domain.entity.СategoryKitchen
import io.reactivex.rxjava3.core.Single

interface IGetKitchens {
    fun getKitchens():Single<List<СategoryKitchen>>
}