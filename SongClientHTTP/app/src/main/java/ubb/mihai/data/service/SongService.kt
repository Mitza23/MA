package ubb.mihai.data.service

import ubb.mihai.data.model.Song
import ubb.mihai.data.remote.SongsApi
import ubb.mihai.data.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongService @Inject constructor(
    val repo: SongRepository,
    val songsApi: SongsApi
//    var songsToDelete: MutableList<Song> = emptyList<Song>() as MutableList<Song>
) {
    suspend fun getRemoteSongs() {
        clearList()
        var result = songsApi.getAll()
        if (result.isSuccessful){
            var list = result.body()
            if (list != null) {
                for (dto in list) {
                    var song = dto.toSong()
                    repo.insert(song)
                }
            }
        }
    }

    suspend fun sendLocalSongs() {
        var flow = repo.getAll()
        flow.collect { list ->
            list.forEach {
                val response = songsApi.addSong(it)
                if (response.isSuccessful) {
                    println("Song added successfully")
                } else {
                    println("Error adding song: ${response.message()}")
                }
            }
        }
    }

    suspend fun insert(song: Song) {
        val response = songsApi.addSong(song)
        if(response.isSuccessful) {
            song.remoteId = response.body()!!
        }
        repo.insert(song)
    }

    suspend fun delete(song: Song) {
        repo.delete(song)
        var result = songsApi.deleteSong(song.remoteId!!)
        if(!result.isSuccessful){
//            songsToDelete.add(song)
        }
    }

//    suspend fun sendDeletedLocal() {
//        songsToDelete.forEach{song ->
//            songsApi.deleteSong(song.remoteId!!)
//        }
//    }

    suspend fun update(song: Song) {
        repo.insert(song)
        songsApi.updateSong(song)
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
//        sendDeletedLocal()
        sendLocalSongs()
        getRemoteSongs()
        return repo.getAll()
    }
}