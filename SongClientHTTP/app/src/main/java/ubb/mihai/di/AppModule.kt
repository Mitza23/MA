package ubb.mihai.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ubb.mihai.data.remote.SongsApi
import ubb.mihai.data.repository.SongDatabase
import ubb.mihai.data.repository.SongRepository
import ubb.mihai.data.repository.SongRepositoryImpl
import ubb.mihai.data.service.SongService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.178:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): SongsApi {
        return retrofit.create(SongsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideService(repo: SongRepository, api: SongsApi): SongService {
        return SongService(repo, api)
    }

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