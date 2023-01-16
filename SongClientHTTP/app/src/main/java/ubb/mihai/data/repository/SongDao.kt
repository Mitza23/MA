package ubb.mihai.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ubb.mihai.data.model.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: Song)

    @Delete
    suspend fun delete(song: Song)

    @Query("SELECT * FROM song WHERE localId = :id")
    suspend fun getByLocalId(id: Int): Song?

    @Query("SELECT * FROM song WHERE remoteId = :id")
    suspend fun getByRemoteId(id: Int): Song?

    @Query("SELECT * FROM song")
    fun getAll(): Flow<List<Song>>

    @Query("SELECT * FROM song")
    fun getAllList(): List<Song>
}