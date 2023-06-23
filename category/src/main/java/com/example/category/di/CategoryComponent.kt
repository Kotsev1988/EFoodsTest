package com.example.category.di
//
//import com.example.efoods.di.modules.DetailViewModelModule
//import com.example.efoods.di.modules.ViewModelModule
//import com.example.category.di.scopes.CategoryScope
//import com.example.efoods.presentation.adapters.dishes.DishesGridAdapter
//import com.example.efoods.presentation.adapters.menus.CategoryHorizontalAdapter
//import com.example.efoods.presentation.fragment.CategoryFragment
//import com.example.efoods.presentation.fragment.DetailFragment
//import com.example.core.di.BaseComponent
//import com.example.efoods.di.modules.ViewModelFactoryModule
//import dagger.Component
//
//@CategoryScope
//@Component(
//    dependencies = [BaseComponent::class],
//    modules = [com.example.efoods.di.modules.ViewModelFactoryModule::class, com.example.efoods.di.modules.ViewModelModule::class, com.example.efoods.di.modules.DetailViewModelModule::class]
//)
//
//interface CategoryComponent {
//
//    @Component.Factory
//    interface Factory{
//        fun create (baseComponent: BaseComponent): CategoryComponent
//    }
//
//    fun inject(categoryFragment: com.example.efoods.presentation.fragment.CategoryFragment)
//    fun inject(detailFragment: com.example.efoods.presentation.fragment.DetailFragment)
//
//
//    fun inject(bestSellersProductAdapter: com.example.efoods.presentation.adapters.dishes.DishesGridAdapter)
//    fun inject(categoryHorizontalAdapter: com.example.efoods.presentation.adapters.menus.CategoryHorizontalAdapter)
//}