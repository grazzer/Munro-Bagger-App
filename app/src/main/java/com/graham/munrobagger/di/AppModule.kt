package com.graham.munrobagger.di

import android.content.Context
import androidx.room.Room
import com.graham.munrobagger.data.local.dao.MyClimbsDao
import com.graham.munrobagger.data.local.databases.MyClimbsDB
import com.graham.munrobagger.data.remote.MunroApi
import com.graham.munrobagger.repository.MunroRepository
import com.graham.munrobagger.repository.MyClimbsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideMYClimbsRepository(
        dao: MyClimbsDao
    ) = MyClimbsRepository(dao)

    @Singleton
    @Provides
    fun providesMyClimbsDB(@ApplicationContext context: Context):MyClimbsDB {
        return Room.databaseBuilder(
            context = context,
            MyClimbsDB::class.java,
            "myData.db")
            .build()
    }

    @Singleton
    @Provides
    fun providesMyClimbsDao(db: MyClimbsDB): MyClimbsDao {
        return db.MyClimbsDao()
    }
}