package ubb.mihai.data.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import ubb.mihai.data.model.Song
import ubb.mihai.data.remote.SongsApi
import ubb.mihai.data.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SongService @Inject constructor(
    val repo: SongRepository,
    val songsApi: SongsApi,
    var songsToDelete: MutableList<Song> = mutableListOf<Song>()
) {
    suspend fun getRemoteSongs() {
        try {
            var result = songsApi.getAll()
            if (result.isSuccessful) {
                var list = result.body()
                if (list != null) {
                    for (dto in list) {
                        var song = dto.toSong()
                        println(song)
                        repo.insert(song)
                    }
                }
            }
        }
        catch (e : Exception){
            return
        }
    }

    suspend fun sendLocalSongs() {
//        var items
        val flowValue: List<Song>
        runBlocking(Dispatchers.IO) {
            flowValue = repo.getAll().first()
        }
        flowValue.forEach{
            insertRemote(it)
            println(it)
        }
//        var flow = repo.getAll()
//        flow.collect { list ->
//            list.forEach {
//                insertRemote(it)
//                println(it)
//            }
//        }
    }

    suspend fun insertRemote(song: Song) {
        try {
            val response = songsApi.addSong(song.toDTO())
            if (response.isSuccessful) {
                song.remoteId = response.body()!!
            }
        } catch (e: Exception) {
            return
        }
    }

    suspend fun insert(song: Song) {
        repo.insert(song)
        insertRemote(song)
    }

    suspend fun deleteRemote(song: Song): Boolean {
        return try {
            var result = songsApi.deleteSong(song.remoteId!!)
            if (!result.isSuccessful) {
                return false
            }
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun delete(song: Song) {
        repo.delete(song)
        val success = deleteRemote(song)
        if (!success) {
            songsToDelete.add(song)
        }
    }

    suspend fun sendDeletedLocal() {
        songsToDelete.forEach { song ->
            var success = deleteRemote(song)
            if (success) {
                songsToDelete.remove(song)
            }
        }

    }

    suspend fun updateRemote(song: Song) {
        try {
            songsApi.updateSong(song.toDTO())
        } catch (e: Exception) {
            return
        }
    }

    suspend fun update(song: Song) {
        repo.insert(song)
        updateRemote(song)
    }

    suspend fun getByLocalId(id: Int): Song? {
        return repo.getByLocalId(id)
    }

    suspend fun getByRemoteId(id: Int): Song? {
        return repo.getByRemoteId(id)
    }

    suspend fun clearList() {
        repo.clearList()
    }

    suspend fun getAll(): Flow<List<Song>> {
        runBlocking(Dispatchers.Main) {
            sendLocalSongs()
        }
        println("Sent local songs")
        runBlocking(Dispatchers.Main) {
            clearList()
        }
        sendDeletedLocal()
        println("Sent local deleted")
        println("Cleared list")
        runBlocking(Dispatchers.Main) {
            getRemoteSongs()
        }
        println("Got remote")
        return repo.getAll()
    }
}