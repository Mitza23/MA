package ubb.mihai.ui.song_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ubb.mihai.util.Routes
import ubb.mihai.util.UiEvent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ubb.mihai.data.model.Song
import ubb.mihai.data.repository.SongRepository
import ubb.mihai.data.service.SongService
import ubb.mihai.ui.song_list.SongListEvent
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val repository: SongRepository
) : ViewModel() {

    val songs = repository.getAll()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedSong: Song? = null

    fun onEvent(event: SongListEvent) {
        when (event) {
            is SongListEvent.OnSongClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_SONG + "?songId=${event.song.localId}"))
            }
            is SongListEvent.OnAddSongClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_SONG))
            }
            is SongListEvent.OnUndoDeleteClick -> {
                deletedSong?.let { song ->
                    viewModelScope.launch {
                        repository.insert(song)
                    }
                }
            }
            is SongListEvent.OnDeleteSongClick -> {
                viewModelScope.launch {
                    repository.delete(event.song)
                    deletedSong = event.song
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Song deleted",
                        action = "Undo"
                    ))
                }
            }
            is SongListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insert(event.song.copy())
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}