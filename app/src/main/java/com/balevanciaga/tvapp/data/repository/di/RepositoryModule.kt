package com.balevanciaga.tvapp.data.repository.di

import com.balevanciaga.tvapp.data.repository.TvShowRepository
import com.balevanciaga.tvapp.domain.repository.ITvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTvShowRepository(repo: TvShowRepository): ITvShowRepository
}