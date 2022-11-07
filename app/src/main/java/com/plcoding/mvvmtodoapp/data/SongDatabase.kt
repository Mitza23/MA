package com.plcoding.mvvmtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Song::class],
    version = 1
)
abstract class SongDatabase: RoomDatabase() {

    abstract val dao: SongDao
}