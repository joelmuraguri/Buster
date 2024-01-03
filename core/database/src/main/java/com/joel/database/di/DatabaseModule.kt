package com.joel.database.di

import android.content.Context
import androidx.room.Room
import com.joel.database.BusterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(
        context: Context
    ) : BusterDatabase{
        return Room.databaseBuilder(
            context,
            BusterDatabase::class.java,
            "buster_database"
        ).build()
    }

}