package com.graham.munrobagger.di

import com.graham.munrobagger.data.remote.MunroApi
import com.graham.munrobagger.repository.MunroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMunroRepository(
        api: MunroApi
    ) = MunroRepository(api)

    @Singleton
    @Provides
    fun providesMunroApi(): MunroApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://hill-bagging-api.onrender.com/")
            .build()
            .create(MunroApi::class.java)
    }
}