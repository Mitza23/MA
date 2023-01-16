package ubb.mihai.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ubb.mihai.data.remote.RetrofitHelper
import ubb.mihai.data.remote.SongsApi
import ubb.mihai.data.repository.SongDatabase
import ubb.mihai.data.repository.SongRepository
import ubb.mihai.data.repository.SongRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    @Singleton
//    fun provideSongsApi(): SongsApi {
//        return RetrofitHelper.getInstances().create(SongsApi::class.java)
//    }

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