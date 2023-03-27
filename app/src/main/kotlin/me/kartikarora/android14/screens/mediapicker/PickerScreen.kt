package me.kartikarora.android14.screens.mediapicker

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.kartikarora.android14.activities.HomeActivity
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.utils.SetupM3Scaffold
import me.kartikarora.android14.utils.findActivity

@Composable
fun PickerScreen(
    paddingValues: PaddingValues
) {
    var selectImages by remember { mutableStateOf(listOf<Uri?>()) }
    var showImagesFromContentResolver by remember { mutableStateOf(false) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectImages = uris }
    )
    val partialAccessPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            val partialPermissionGranted =
                it[Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED] ?: false
            showImagesFromContentResolver = partialPermissionGranted
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        Button(
            onClick = {
                showImagesFromContentResolver = false
                partialAccessPermission.launch(
                    arrayOf(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                    )
                )
            }
        ) {
            Text(text = "Launch permission dialog")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
                showImagesFromContentResolver = false
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
        ) {
            Text(text = "Launch photo picker")
        }

        if (showImagesFromContentResolver) {
            selectImages =
                (LocalContext.current.findActivity() as HomeActivity).getGrantedImageUris()
        }

        if (selectImages.isNotEmpty()) {
            LazyColumn {
                items(selectImages.size) { index ->
                    val imageUri = selectImages[index]
                    if (imageUri != null) {
                        Spacer(modifier = Modifier.height(16.dp))
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BackLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.BackGesture) { paddingValues ->
            PickerScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun BackDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.BackGesture) { paddingValues ->
            PickerScreen(paddingValues)
        }
    }
}