package com.example.efoods.di.modules

import androidx.lifecycle.ViewModel
import com.example.efoods.presentation.viewModel.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class DetailViewModelModule {
    @Singleton
@Binds
@IntoMap
@com.example.efoods.di.modules.ViewModelKey(DetailViewModel::class)
internal abstract fun bindMyViewModel(viewModel: DetailViewModel): ViewModel
}