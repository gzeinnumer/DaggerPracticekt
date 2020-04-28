package com.gzeinnumer.daggerpracticekt.di.main

import androidx.lifecycle.ViewModel
import com.gzeinnumer.daggerpracticekt.di.ViewModelKey
import com.gzeinnumer.daggerpracticekt.ui.main.profile.ProfileVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileVM::class)
    abstract fun bidProfileViewModel(profileVM: ProfileVM): ViewModel
}