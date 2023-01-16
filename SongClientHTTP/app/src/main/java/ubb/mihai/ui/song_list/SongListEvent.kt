package ubb.mihai.ui.song_list

import ubb.mihai.data.model.Song


sealed class SongListEvent{
    data class OnDeleteSongClick(val song: Song): SongListEvent()
    data class OnDoneChange(val song: Song, val isDone: Boolean): SongListEvent()
    object OnUndoDeleteClick: SongListEvent()
    data class OnSongClick(val song: Song): SongListEvent()
    object OnAddSongClick: SongListEvent()
}
