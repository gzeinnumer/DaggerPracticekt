package com.gzeinnumer.daggerpracticekt.di

import androidx.lifecycle.ViewModelProvider
import com.gzeinnumer.daggerpracticekt.vm.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelFactoryModule{
    @Binds
    internal abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}