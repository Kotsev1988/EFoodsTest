package com.example.efoods.di.modules

import androidx.lifecycle.ViewModel
import com.example.efoods.presentation.viewModel.MyCardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class MyCardViewModuleModel {
    @Singleton
    @Binds
    @IntoMap
    @com.example.efoods.di.modules.ViewModelKey(MyCardViewModel::class)
    internal abstract fun bindMyViewModel(viewModel: MyCardViewModel): ViewModel
}