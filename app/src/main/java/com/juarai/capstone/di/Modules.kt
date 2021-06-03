package com.juarai.capstone.di

import androidx.room.Room
import com.juarai.capstone.data.local.AppDatabase
import com.juarai.capstone.data.network.Endpoint
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val roomModule = module {
    factory { get<AppDatabase>().userDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "juarai.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val retrofitModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://neat-coast-314213.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(Endpoint::class.java)
    }
}
