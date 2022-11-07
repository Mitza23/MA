package com.plcoding.mvvmtodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    val title : String,
    val artist : String,
    val genre : String,
    val year : Int,
    val link : String,
    @PrimaryKey
    val id : Int?,
)
