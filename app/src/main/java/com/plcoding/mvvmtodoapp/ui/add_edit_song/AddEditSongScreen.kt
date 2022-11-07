package com.plcoding.mvvmtodoapp.ui.add_edit_song

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.snackbar.Snackbar
import com.plcoding.mvvmtodoapp.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditSongScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditSongViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
           when(event) {
               is UiEvent.PopBackStack -> onPopBackStack()
               is UiEvent.ShowSnackbar -> {
                   scaffoldState.snackbarHostState.showSnackbar(
                       message = event.message,
                       actionLabel = event.action
                   )
               }
               else -> Unit
           }
        }
    }
    Scaffold (
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
               viewModel.onEvent(AddEditSongEvent.OnSaveSongClick)
            }) {
                Icon(
                    imageVector =  Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(AddEditSongEvent.OnTitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.artist,
                onValueChange = {
                    viewModel.onEvent(AddEditSongEvent.OnArtistChange(it))
                },
                placeholder = {
                    Text(text = "Artist")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.genre,
                onValueChange = {
                    viewModel.onEvent(AddEditSongEvent.OnGenreChange(it))
                },
                placeholder = {
                    Text(text = "Genre")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.genre,
                onValueChange = {
                    viewModel.onEvent(AddEditSongEvent.OnGenreChange(it))
                },
                placeholder = {
                    Text(text = "Genre")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.year.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditSongEvent.OnYearChange(Integer.parseInt(it)))
                },
                placeholder = {
                    Text(text = "Year")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.link,
                onValueChange = {
                    viewModel.onEvent(AddEditSongEvent.OnLinkChange(it))
                },
                placeholder = {
                    Text(text = "Link")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}