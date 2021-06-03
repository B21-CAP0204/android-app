package com.juarai.capstone

import android.app.Application
import com.juarai.capstone.di.retrofitModule
import com.juarai.capstone.di.roomModule
import com.juarai.capstone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BaseApplication)
            modules(
                roomModule,
                retrofitModule,
                viewModelModule
            )
        }
    }
}