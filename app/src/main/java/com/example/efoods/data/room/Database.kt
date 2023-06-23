package com.example.efoods.data.room

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.efoods.data.room.dishes.dao.DishesDao
import com.example.efoods.data.room.dishes.entity.RoomDishes
import com.example.efoods.data.room.kitchen.dao.KitchenDao
import com.example.efoods.data.room.kitchen.entity.RoomKitchen
import com.example.efoods.data.room.myCard.dao.MyCardDao
import com.example.efoods.data.room.myCard.entity.RoomMyCard
import com.example.efoods.data.room.utils.TegsConverter

@androidx.room.Database(entities = [RoomKitchen::class, RoomDishes::class, RoomMyCard::class], version = 2)
@TypeConverters(TegsConverter::class)
abstract class Database : RoomDatabase() {

    abstract val kitchenDao: KitchenDao
    abstract val dishesDao: DishesDao
    abstract val myCardDao: MyCardDao

    companion object {
        const val DB_NAME = "database.db"
    }
}