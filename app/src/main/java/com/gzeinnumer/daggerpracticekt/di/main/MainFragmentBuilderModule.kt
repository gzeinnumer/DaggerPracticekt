package com.gzeinnumer.daggerpracticekt.di.main

import com.gzeinnumer.daggerpracticekt.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun constributeProfileFragment(): ProfileFragment
}