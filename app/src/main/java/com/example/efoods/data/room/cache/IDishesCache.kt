package com.example.efoods.data.room.cache

import com.example.efoods.domain.entity.Dishe
import com.example.efoods.domain.entity.СategoryKitchen
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

interface IDishesCache {
    fun insertDishesToDB( kitchens: List<Dishe>): Single<List<Dishe>>
    fun getDishesFromCache(): Single<List<Dishe>>

}