package com.example.efoods.di.modules

import com.example.efoods.data.GetDishesImpl
import com.example.efoods.data.GetKitchensImpl
import com.example.efoods.data.MyCardProductsImpl
import com.example.efoods.data.api.IFoodAPI
import com.example.efoods.data.room.Database
import com.example.efoods.data.room.cache.IDishesCache
import com.example.efoods.data.room.cache.IKitchensCache
import com.example.efoods.domain.entity.repository.IGetDishes
import com.example.efoods.domain.entity.repository.IGetKitchens
import com.example.efoods.domain.myCard.IMyCardProducts
import com.example.efoods.util.INetworkStates
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun getKitchens(api: IFoodAPI,
                    networkStatus: INetworkStates,
                    kitchensCash: IKitchensCache
    ): IGetKitchens = GetKitchensImpl(api, networkStatus, kitchensCash)

    @Singleton
    @Provides
    fun getDishes(api: IFoodAPI,
                  networkStatus: INetworkStates,
                  dishesCache: IDishesCache
    ): IGetDishes = GetDishesImpl(api, networkStatus, dishesCache)

    @Singleton
    @Provides
    fun myCardProduct(
        db: Database
    ): IMyCardProducts = MyCardProductsImpl(db)

}