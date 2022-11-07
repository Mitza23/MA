package com.plcoding.mvvmtodoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: Song)

    @Delete
    suspend fun delete(song: Song)

    @Query("SELECT * FROM song WHERE id = :id")
    suspend fun getById(id: Int): Song?

    @Query("SELECT * FROM song")
    fun getAll(): Flow<List<Song>>
}