package ubb.mihai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ubb.mihai.data.remote.dto.SongDTO

@Entity
data class Song(
    var remoteId: Int?,
    val title : String,
    val artist : String,
    val genre : String,
    val year : Int,
    val link : String,
    @PrimaryKey
    val localId : Int?,
) {
    fun toDTO(): SongDTO{
        return SongDTO(
            id = remoteId,
            title = title,
            artist = artist,
            genre = genre,
            year = year,
            link = link
        )
    }
}
