package com.balevanciaga.tvapp.data.dataSources.local.room.di

import android.content.Context
import androidx.room.Room
import com.balevanciaga.tvapp.data.dataSources.local.room.TvShowDatabase
import com.balevanciaga.tvapp.data.dataSources.local.room.dao.TvGenresDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context,
    ): TvShowDatabase = Room
        .databaseBuilder(context, TvShowDatabase::class.java, "db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideTvGenresDao(database: TvShowDatabase): TvGenresDao = database.tvGenresDao()
}