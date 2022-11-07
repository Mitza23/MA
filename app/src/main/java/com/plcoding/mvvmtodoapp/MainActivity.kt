package com.plcoding.mvvmtodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.mvvmtodoapp.ui.add_edit_song.AddEditSongScreen
import com.plcoding.mvvmtodoapp.ui.song_list.SongListScreen
import com.plcoding.mvvmtodoapp.ui.theme.MVVMTodoAppTheme
import com.plcoding.mvvmtodoapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.SONG_LIST
                ) {
                    composable(Routes.SONG_LIST){
                        SongListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(
                        route = Routes.ADD_EDIT_SONG + "?songId={songId}",
                        arguments = listOf(
                            navArgument(name = "songId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditSongScreen(onPopBackStack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}