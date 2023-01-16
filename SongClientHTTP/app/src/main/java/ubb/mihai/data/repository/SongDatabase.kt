package ubb.mihai.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import ubb.mihai.data.model.Song
import ubb.mihai.data.repository.SongDao

@Database(
    entities = [Song::class],
    version = 1
)
abstract class SongDatabase: RoomDatabase() {

    abstract val dao: SongDao
}