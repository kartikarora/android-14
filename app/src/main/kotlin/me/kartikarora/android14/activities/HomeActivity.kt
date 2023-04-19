package me.kartikarora.android14.activities

import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.nav.NavigationHost
import me.kartikarora.android14.screens.home.HomeScreen
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.viewmodels.HomeViewModel

class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navHostController = rememberNavController()
            Android14Theme {
                SetupM3Scaffold(viewModel.currentScreen) { paddingValues ->
                    NavigationHost(
                        navHostController = navHostController,
                        paddingValues = paddingValues
                    ) { destination ->
                        when (destination) {
                            Destination.Home,
                            Destination.CustomActionIntentChooser,
                            Destination.RegionalPrefs,
                            Destination.SelectedPhotoAccess -> {
                                viewModel.updateDestination(destination)
                                navHostController.navigate(destination.title)
                            }

                            Destination.GrammaticalInflection -> GrammaticalInflectionActivity.start(
                                this
                            )

                            Destination.BackGesture -> BackGestureActivity.start(this)
                            Destination.ScreenshotDetection -> ScreenshotActivity.start(this)
                            else -> { /* no-op */
                            }
                        }
                    }
                }
            }
        }
    }

    fun getGrantedImageUris(): List<Uri?> {
        val imagesList = mutableListOf<Uri?>()

        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = null
        val selectionArgs = null
        val sortOrder = null

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor: Cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                imagesList.add(contentUri)
            }
        }
        return imagesList
    }
}

@Preview
@Composable
fun HomeLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.Home) { paddingValues ->
            HomeScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun HomeDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.Home) { paddingValues ->
            HomeScreen(paddingValues)
        }
    }
}