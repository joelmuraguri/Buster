package com.joel.buster.di

import com.joel.buster.BuildConfig
import com.joel.network.api.BusterRemoteSource
import com.joel.network.api.impl.BusterRemoteSourceImpl
import com.joel.network.utils.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.AndroidClientEngine
import io.ktor.client.engine.android.AndroidEngineConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine = AndroidClientEngine(AndroidEngineConfig())

    @Provides
    @Singleton
    fun provideHttpClient(engine: HttpClientEngine): HttpClient = HttpClientFactory().createEngine(engine)

    @Provides
    fun providesBusterRemoteSource(
        httpClient: HttpClient,
        @Named("TMDB_API_KEY") apiKey: String
    ): BusterRemoteSource = BusterRemoteSourceImpl(httpClient, apiKey)

    @Provides
    @Named("TMDB_API_KEY")
    fun providesApiKey() = BuildConfig.TMDB_API_KEY

}