package ubb.mihai.data.remote

import ubb.mihai.data.model.Song
import ubb.mihai.data.remote.dto.SongDTO
import retrofit2.Response
import retrofit2.http.*

interface SongsApi {
    @GET
    suspend fun getAll() : Response<List<SongDTO>>

    @POST("/songs")
    suspend fun addSong(@Body song: Song) : Response<Int>

    @PUT("/songs")
    suspend fun updateSong(@Body song: Song) : Response<Boolean>

    @DELETE("/songs/{id}")
    suspend fun deleteSong(@Path("id") id: Int) : Response<Boolean>
}