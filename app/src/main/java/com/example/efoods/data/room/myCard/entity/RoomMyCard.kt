package com.example.efoods.data.room.myCard.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.efoods.data.room.utils.TegsConverter

@Entity
class RoomMyCard (
    var description: String,
    @PrimaryKey var id: Int,
    var image_url: String,
    var name: String,
    var price: Int,
    @TypeConverters(TegsConverter::class)
    var tegs: List<String>,
    var weight: Int,
    var count: Int
        )