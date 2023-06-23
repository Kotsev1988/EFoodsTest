package com.example.efoods.data.room.cache

import com.example.efoods.domain.entity.СategoryKitchen
import io.reactivex.rxjava3.core.Single

interface IKitchensCache {

    fun insertKitchensToDB( kitchens: List<СategoryKitchen>): Single<List<СategoryKitchen>>
    fun getKitchensFromCache(): Single<List<СategoryKitchen>>
}