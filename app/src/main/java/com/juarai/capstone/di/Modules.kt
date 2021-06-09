package com.juarai.capstone.di

import androidx.room.Room
import com.juarai.capstone.BuildConfig
import com.juarai.capstone.data.Repository
import com.juarai.capstone.data.local.AppDatabase
import com.juarai.capstone.data.local.UserDao
import com.juarai.capstone.data.network.Endpoint
import com.juarai.capstone.ui.camera.CameraViewModel
import com.juarai.capstone.ui.home.HomeViewModel
import com.juarai.capstone.ui.register.RegisterViewModel
import com.juarai.capstone.ui.validation.ValidationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Local Data
val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "juarai.db"
        ).fallbackToDestructiveMigration().build()
    }

    fun provideDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    single { provideDao(get()) }
}

// Network
val retrofitModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(Endpoint::class.java)
    }
}

// Repository
val repositoryModule = module {
    single {
        Repository(get())
    }
}

// View Model
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { CameraViewModel() }
    viewModel { ValidationViewModel(get()) }
}
