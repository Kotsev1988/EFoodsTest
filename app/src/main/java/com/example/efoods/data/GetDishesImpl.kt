package com.example.efoods.data

import com.example.efoods.data.api.IFoodAPI
import com.example.efoods.util.INetworkStates
import com.example.efoods.data.room.cache.IDishesCache
import com.example.efoods.domain.entity.Dishe
import com.example.efoods.domain.entity.repository.IGetDishes
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetDishesImpl(private val api: IFoodAPI,
                    private val networkStatus: INetworkStates,
                    private val dishesCash: IDishesCache
): IGetDishes {
    override fun getDishes(): Single<List<Dishe>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {

            api.getDishes()
                .flatMap {

                dishesCash.insertDishesToDB(it.dishes)
            }?: Single.error<List<Dishe>>(RuntimeException("no dishes "))
                .subscribeOn(Schedulers.io())
        }else{
            dishesCash.getDishesFromCache()
        }.subscribeOn(Schedulers.io())
    }
}