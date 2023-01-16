package ubb.mihai.data.repository


import ubb.mihai.data.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    suspend fun insert(song: Song)

    suspend fun delete(song: Song)

    suspend fun getByLocalId(id: Int): Song?

    suspend fun getByRemoteId(id: Int): Song?

    suspend fun clearList()

    fun getAll(): Flow<List<Song>>
}