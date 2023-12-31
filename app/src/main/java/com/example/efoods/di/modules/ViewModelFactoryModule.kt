package com.example.efoods.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.efoods.presentation.viewModel.ViewModelFactory

import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}