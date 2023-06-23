package com.example.efoods.di.modules

import androidx.lifecycle.ViewModel
import com.example.efoods.presentation.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModuleMain {
    @Singleton
    @Binds
    @IntoMap
    @com.example.efoods.di.modules.ViewModelKey(MainViewModel::class)
    internal abstract fun bindMyViewModel(viewModel: MainViewModel): ViewModel
}