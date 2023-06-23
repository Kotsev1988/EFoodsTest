package com.example.main.di

//import com.example.core.di.BaseComponent
//import com.example.efoods.di.modules.ViewModelFactoryModule
//import com.example.efoods.di.modules.MainModule
//import com.example.efoods.di.modules.ViewModelModuleMain
//import com.example.main.di.scopes.MainScope
//import com.example.efoods.presentation.adapters.MainAdapter
//import com.example.efoods.presentation.fragment.MainFragment
//import dagger.Component
//@MainScope
//@Component(
//    dependencies = [BaseComponent::class],
//    modules = [com.example.efoods.di.modules.MainModule::class,  com.example.efoods.di.modules.ViewModelFactoryModule::class, com.example.efoods.di.modules.ViewModelModuleMain::class]
//)
//interface MainComponent {
//
//    @Component.Factory
//    interface Factory{
//        fun create (baseComponent: BaseComponent): MainComponent
//    }
//
//    fun inject(mainFragment: com.example.efoods.presentation.fragment.MainFragment)
//    fun inject(mainAdapter: com.example.efoods.presentation.adapters.MainAdapter)
//}