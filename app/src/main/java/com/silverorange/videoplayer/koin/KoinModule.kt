package com.silverorange.videoplayer.koin

import com.silverorange.videoplayer.services.WebServices
import com.silverorange.videoplayer.utilities.Constants
import com.silverorange.videoplayer.utilities.ZonedDataTimeMoshiAdapter
import com.silverorange.videoplayer.viewModels.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//A module is a logical space to help you organize your definitions
val appModule = module {

    // single instance of Moshi
    single<Moshi> {
        Moshi.Builder().add(ZonedDataTimeMoshiAdapter()).add(KotlinJsonAdapterFactory()).build()
    }

    // single instance of OkHttpClient
    single {
        OkHttpClient.Builder().build()
    }

    // single instance of WebServices
    single<WebServices> {
        val retrofit =
            Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
                .client(get())
                .build()

        retrofit.create(WebServices::class.java)
    }

    //viewModels
    viewModel { MainViewModel(get()) }
}