package com.example.efoods.data

import com.example.efoods.data.api.IFoodAPI
import com.example.efoods.util.INetworkStates
import com.example.efoods.data.room.cache.IKitchensCache
import com.example.efoods.domain.entity.repository.IGetKitchens
import com.example.efoods.domain.entity.СategoryKitchen
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetKitchensImpl(
    private val api: IFoodAPI,
    private val networkStatus: INetworkStates,
    private val kitchensCash: IKitchensCache,
) : IGetKitchens {

    override fun getKitchens(): Single<List<СategoryKitchen>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {


                api.getKitchens().flatMap {
                    kitchensCash.insertKitchensToDB(it.сategories)
                } ?: Single.error<List<СategoryKitchen>>(RuntimeException("no category "))
                    .subscribeOn(Schedulers.io())
            } else {
                kitchensCash.getKitchensFromCache()
            }.subscribeOn(Schedulers.io())
        }
}