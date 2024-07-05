package com.webapp.common.di

import org.koin.core.context.startKoin

public fun initKoin_ios() {
    startKoin {
        modules(
            viewModelModule
        )
    }
}