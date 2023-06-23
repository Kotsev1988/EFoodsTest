package com.example.efoods.di.modules

import android.widget.ImageView
import com.example.efoods.data.image.LoadImage
import com.example.efoods.domain.image.ILoadImage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoadImageModule {
    @Singleton
    @Provides
    fun loadImage() : ILoadImage<ImageView> = LoadImage()
}