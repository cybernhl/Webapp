package com.webapp.common.di

import com.webapp.common.ui.main.MainViewModel
import com.webapp.common.ui.splash.SplashPage
import com.webapp.common.ui.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

//Ref : https://insert-koin.io/docs/reference/koin-android/viewmodel/
//Ref : https://medium.com/@ericsilva328/android-koin-dependency-injection-for-beginners-41c8299c2eb4
public val viewModelModule: Module = module {
    factory {
        SplashViewModel()
    }
    factory {
        MainViewModel()
    }
}