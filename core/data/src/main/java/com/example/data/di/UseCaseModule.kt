package com.example.data.di

import com.example.domain.GetFilmDetailsUseCase
import com.example.domain.GetLatestFilmUseCase
import com.example.network.repo.FilmRepo
import com.example.network.repo.FilmRepoImpl
import com.example.network.service.BusterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    @Singleton
    fun provideFilmRepository(
        apiService: BusterApiService
    ) : FilmRepo{
       return  FilmRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetLatestFilmUseCase(repo: FilmRepo) : GetLatestFilmUseCase{
        return GetLatestFilmUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetFilmDetailsUseCase(repo: FilmRepo) : GetFilmDetailsUseCase{
        return GetFilmDetailsUseCase(repo)
    }

}