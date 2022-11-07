package com.plcoding.mvvmtodoapp.data

import kotlinx.coroutines.flow.Flow

class SongRepositoryImpl(
    private val dao: SongDao
) : SongRepository{
    override suspend fun insert(song: Song) {
        dao.insert(song)
    }

    override suspend fun delete(song: Song) {
        dao.delete(song)
    }

    override suspend fun getById(id: Int): Song? {
        return dao.getById(id)
    }

    override fun getAll(): Flow<List<Song>> {
        return dao.getAll()
    }
}