package ubb.mihai


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ubb.mihai.ui.add_edit_song.AddEditSongScreen
import ubb.mihai.ui.song_list.SongListScreen
import ubb.mihai.ui.theme.SongClientHTTPTheme
import ubb.mihai.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SongClientHTTPTheme {
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