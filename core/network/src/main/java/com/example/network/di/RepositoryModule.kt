package com.example.network.di

import com.example.network.repo.PagedFilmsRepository
import com.example.network.service.BusterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePagedFilmsRepository(apiService: BusterApiService) = PagedFilmsRepository(apiService = apiService)

}