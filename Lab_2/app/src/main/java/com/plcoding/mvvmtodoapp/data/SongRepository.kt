package com.plcoding.mvvmtodoapp.data


import kotlinx.coroutines.flow.Flow

interface SongRepository {

    suspend fun insert(song: Song)

    suspend fun delete(song: Song)

    suspend fun getById(id: Int): Song?

    fun getAll(): Flow<List<Song>>
}