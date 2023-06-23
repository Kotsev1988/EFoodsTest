package com.example.efoods.data.room.cache.room

import com.example.efoods.data.room.Database
import com.example.efoods.data.room.cache.IKitchensCache
import com.example.efoods.data.room.kitchen.entity.RoomKitchen
import com.example.efoods.domain.entity.СategoryKitchen
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class KitchensCache(private val db: Database): IKitchensCache {

    override fun insertKitchensToDB(kitchens: List<СategoryKitchen>): Single<List<СategoryKitchen>> =
        Single.fromCallable {
            val roomKitchens = kitchens.map {kitchen ->
                RoomKitchen(id = kitchen.id, image_url = kitchen.image_url, name = kitchen.name)
            }
            db.kitchenDao.insert(roomKitchens)
            kitchens
        }.subscribeOn(Schedulers.io())




    override fun getKitchensFromCache(): Single<List<СategoryKitchen>> = Single.fromCallable {
        val kitchen : ArrayList<СategoryKitchen> = arrayListOf()
       val kitchensFromDB = db.kitchenDao.getAll()
        kitchensFromDB.map {
            kitchen.add(СategoryKitchen(it.id, it.image_url, it.name))
        }
        kitchen
    }
}