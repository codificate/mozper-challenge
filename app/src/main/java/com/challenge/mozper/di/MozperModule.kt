package com.challenge.mozper.di

import android.content.Context
import androidx.room.Room
import com.challenge.mozper.api.RetrofitInstance
import com.challenge.mozper.data.api.MozperApi
import com.challenge.mozper.data.database.MozperDatabase
import com.challenge.mozper.data.repository.MozperApiRepositoryImpl
import com.challenge.mozper.domain.repository.MozperApiRepository
import com.challenge.mozper.domain.repository.MozperDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MozperModule {

    const val MOZPER_DATABASE_NAME = "employees"

    @Singleton
    @Provides
    fun provideMozperDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MozperDatabase::class.java, MOZPER_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMozperDAO(appDatabase: MozperDatabase): MozperDaoRepository {
        return appDatabase.employeesDao()
    }

    @Provides
    @Singleton
    fun provideMozperApi(): MozperApi {
        return RetrofitInstance.BUILDER.create(MozperApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMozperApiRepository(api: MozperApi): MozperApiRepository {
        return MozperApiRepositoryImpl(api)
    }

}