package com.gzeinnumer.daggerpracticekt.di

import com.gzeinnumer.daggerpracticekt.di.auth.AuthModule
import com.gzeinnumer.daggerpracticekt.di.auth.AuthViewModelsModule
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthActivity
import com.gzeinnumer.daggerpracticekt.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{
    //catatan penting
    /**
     * setelah ini @ContributesAndroidInjector akan berubah, sebelum berubah
     * tujuan kodingan tampa SubComponent, setiap ViewModel di pakage bisa di akses oleh siapapun activitynya,
     * MainVM diakses oleh MainActivity, SecondVM diakses oleh SecondActivity, ThirdVM diakses oleh ThirdActivity
     * tapi, tampa sub komponent ThirdActivity bisa mengakses SecondVM, MainVM, dan ThirdVM sekaligus,
     * bagaimana caranya agar tidak bsa di akses? caranya adalah dengan memakai subcomponent,
     * memastikan hanya MainActivity, yang bisa mengakses MainVM
     */
    //for all
//    @ContributesAndroidInjector
//    abstract fun constributeAuthActivity(): AuthActivity
    //for u only
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun constributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun constributeMainActivity(): MainActivity
}