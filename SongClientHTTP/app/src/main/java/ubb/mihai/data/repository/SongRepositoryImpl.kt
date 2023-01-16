package ubb.mihai.data.repository

import ubb.mihai.data.model.Song
import kotlinx.coroutines.flow.Flow
import ubb.mihai.data.repository.SongDao
import ubb.mihai.data.repository.SongRepository

class SongRepositoryImpl(
    private val dao: SongDao
) : SongRepository {
    override suspend fun insert(song: Song) {
        dao.insert(song)
    }

    override suspend fun delete(song: Song) {
        dao.delete(song)
    }

    override suspend fun getByLocalId(id: Int): Song? {
        return dao.getByLocalId(id)
    }

    override suspend fun getByRemoteId(id: Int): Song? {
        return dao.getByRemoteId(id)
    }

    override suspend fun clearList() {
        var list = dao.getAllList()
        for(song in list){
            dao.delete(song)
        }
    }

    override fun getAll(): Flow<List<Song>> {
        return dao.getAll()
    }
}