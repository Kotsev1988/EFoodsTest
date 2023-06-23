package com.example.efoods.domain.entity.repository

import com.example.efoods.domain.entity.Dishe
import com.example.efoods.domain.entity.СategoryKitchen
import io.reactivex.rxjava3.core.Single

interface IGetDishes {
    fun getDishes(): Single<List<Dishe>>
}