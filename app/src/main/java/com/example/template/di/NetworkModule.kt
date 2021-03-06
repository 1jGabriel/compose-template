package com.example.template.di

import com.example.template.data.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(client = get()) }
    single { provideApi(get()) }
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
