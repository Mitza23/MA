package ubb.mihai.data.remote.dto

import ubb.mihai.data.model.Song


@kotlinx.serialization.Serializable
data class SongDTO (
    val id: Int,
    val title: String,
    val artist: String,
    val genre: String,
    val year: Int,
    val link: String
) {
    fun toSong(): Song {
        return Song(
            remoteId = id,
            title = title,
            artist = artist,
            genre = genre,
            year = year,
            link = link,
            localId = null
        )
    }
}