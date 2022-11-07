package com.plcoding.mvvmtodoapp.ui.add_edit_song

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mvvmtodoapp.data.Song
import com.plcoding.mvvmtodoapp.data.SongRepository
import com.plcoding.mvvmtodoapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditSongViewModel @Inject constructor(
    private val repository: SongRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var song by mutableStateOf<Song?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var artist by mutableStateOf("")
        private set

    var genre by mutableStateOf("")
        private set

    var year by mutableStateOf(0)
        private set

    var link by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val songId = savedStateHandle.get<Int>("songId")
        if(songId != -1) {
            viewModelScope.launch {
                repository.getById(songId!!)?.let { song ->
                    title = song.title
                    artist = song.artist
                    genre = song.genre
                    year = song.year
                    link = song.link ?: ""
                    this@AddEditSongViewModel.song = song
                }
            }
        }
    }

    fun onEvent(event: AddEditSongEvent) {
        when(event) {
            is AddEditSongEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditSongEvent.OnArtistChange -> {
                artist = event.artist
            }
            is AddEditSongEvent.OnGenreChange -> {
                genre = event.genre
            }
            is AddEditSongEvent.OnYearChange -> {
                year = event.year
            }
            is AddEditSongEvent.OnLinkChange -> {
                link = event.link
            }
            is AddEditSongEvent.OnSaveSongClick -> {
                viewModelScope.launch {
                    if(title.isBlank() && artist.isBlank() && genre.isBlank() && year <= 0) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "Fields can't be empty"
                        ))
                        return@launch
                    }
                    repository.insert(
                        Song(
                            title = title,
                            artist = artist,
                            genre = genre,
                            year = year,
                            link = link,
                            id = song?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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