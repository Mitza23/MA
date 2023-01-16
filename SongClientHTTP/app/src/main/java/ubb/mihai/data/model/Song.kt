package ubb.mihai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
