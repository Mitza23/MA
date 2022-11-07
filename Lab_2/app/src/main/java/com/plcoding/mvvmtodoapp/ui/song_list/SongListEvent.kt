package com.plcoding.mvvmtodoapp.ui.song_list

import com.plcoding.mvvmtodoapp.data.Song

sealed class SongListEvent{
    data class OnDeleteSongClick(val song: Song): SongListEvent()
    data class OnDoneChange(val song: Song, val isDone: Boolean): SongListEvent()
    object OnUndoDeleteClick: SongListEvent()
    data class OnSongClick(val song: Song): SongListEvent()
    object OnAddSongClick: SongListEvent()
}
