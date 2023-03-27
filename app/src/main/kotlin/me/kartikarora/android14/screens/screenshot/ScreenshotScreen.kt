package me.kartikarora.android14.screens.screenshot

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.utils.SetupM3Scaffold

@Composable
fun ScreenshotScreen(paddingValues: PaddingValues, text: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun ScreenshotLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
            ScreenshotScreen(paddingValues, "Test String")
        }
    }
}


@Preview
@Composable
fun ScreenshotDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
            ScreenshotScreen(paddingValues, "Test String")
        }
    }
}
