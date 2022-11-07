package com.plcoding.mvvmtodoapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.mvvmtodoapp.data.SongDatabase
import com.plcoding.mvvmtodoapp.data.SongRepository
import com.plcoding.mvvmtodoapp.data.SongRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSongDatabase(app: Application): SongDatabase {
        return Room.databaseBuilder(
            app,
            SongDatabase::class.java,
            "song_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSongRepository(db: SongDatabase): SongRepository {
        return SongRepositoryImpl(db.dao)
    }
}