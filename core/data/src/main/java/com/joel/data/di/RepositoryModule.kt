package com.joel.data.di

import com.joel.data.repository.FilmRepository
import com.joel.data.repository.impl.OfflineFirstFilmsRepository
import com.joel.data.repository.SearchRepository
import com.joel.data.repository.impl.SearchRepositoryImpl
import com.joel.database.BusterDatabase
import com.joel.network.api.BusterRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesOfflineFirstFilmRepository(
        remoteSource: BusterRemoteSource,
        db : BusterDatabase
    ) : FilmRepository{
        return OfflineFirstFilmsRepository(remoteSource, db)
    }

    @Provides
    @Singleton
    fun providesSearchRepository(
       remoteSource: BusterRemoteSource
    ) : SearchRepository{
        return SearchRepositoryImpl(remoteSource)
    }


}