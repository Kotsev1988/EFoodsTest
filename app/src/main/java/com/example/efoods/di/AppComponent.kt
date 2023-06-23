package com.example.efoods.di

import com.example.efoods.di.modules.ApiModule
import com.example.efoods.di.modules.AppModule
import com.example.efoods.di.modules.DataModule
import com.example.efoods.di.modules.DatabaseModule
import com.example.efoods.di.modules.DetailViewModelModule
import com.example.efoods.di.modules.LoadImageModule
import com.example.efoods.di.modules.MyCardViewModuleModel
import com.example.efoods.di.modules.ViewModelFactoryModule
import com.example.efoods.di.modules.ViewModelModule
import com.example.efoods.di.modules.ViewModelModuleMain
import com.example.efoods.presentation.activity.MainActivity
import com.example.efoods.presentation.adapters.CategoryHorizontalAdapter
import com.example.efoods.presentation.adapters.DishesGridAdapter
import com.example.efoods.presentation.adapters.MainAdapter
import com.example.efoods.presentation.adapters.MyCardAdapter
import com.example.efoods.presentation.fragments.CategoryFragment
import com.example.efoods.presentation.fragments.DetailFragment
import com.example.efoods.presentation.fragments.MainFragment
import com.example.efoods.presentation.fragments.MyCardFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(

    modules = [
        AppModule::class,
        ApiModule::class,
        DatabaseModule::class,
        DataModule::class,
        LoadImageModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        DetailViewModelModule::class,
        ViewModelModuleMain::class,
        MyCardViewModuleModel::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(categoryFragment: CategoryFragment)
    fun inject(detailFragment: DetailFragment)

    fun inject(bestSellersProductAdapter: DishesGridAdapter)
    fun inject(categoryHorizontalAdapter: CategoryHorizontalAdapter)

    fun inject(mainFragment: MainFragment)
    fun inject(mainAdapter: MainAdapter)

    fun inject(myCardFragment: MyCardFragment)
    fun inject(myCardAdapter: MyCardAdapter)

}