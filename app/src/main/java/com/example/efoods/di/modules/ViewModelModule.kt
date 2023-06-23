package com.example.efoods.di.modules

import androidx.lifecycle.ViewModel
import com.example.efoods.presentation.viewModel.CategoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Singleton
    @Binds
    @IntoMap
    @com.example.efoods.di.modules.ViewModelKey(CategoryViewModel::class)
    internal abstract fun bindMyViewModel(viewModel: CategoryViewModel): ViewModel
}