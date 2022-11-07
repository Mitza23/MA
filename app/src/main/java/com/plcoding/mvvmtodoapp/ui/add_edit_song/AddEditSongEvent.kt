package com.plcoding.mvvmtodoapp.ui.add_edit_song

sealed class AddEditSongEvent {
    data class OnTitleChange(val title: String): AddEditSongEvent()
    data class OnArtistChange(val artist: String): AddEditSongEvent()
    data class OnGenreChange(val genre: String): AddEditSongEvent()
    data class OnYearChange(val year: Int): AddEditSongEvent()
    data class OnLinkChange(val link: String): AddEditSongEvent()

    object OnSaveSongClick: AddEditSongEvent()
}
