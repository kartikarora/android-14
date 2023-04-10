package me.kartikarora.android14.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.theme.Android14Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onClick: (Destination) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .nestedScroll(TopAppBarDefaults.pinnedScrollBehavior().nestedScrollConnection)
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        item {
            ButtonForDemoOf(Destination.ScreenshotDetection, onClick)
            ButtonForDemoOf(Destination.SelectedPhotoAccess, onClick)
            ButtonForDemoOf(Destination.BackGesture, onClick)
            ButtonForDemoOf(Destination.GrammaticalInfliction, onClick)
            ButtonForDemoOf(Destination.IntentChooser, onClick)
        }
    }
}

@Composable
fun ButtonForDemoOf(
    destination: Destination,
    onClick: (Destination) -> Unit
) {
    FilledTonalButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick.invoke(destination) }
    ) {
        Text(text = destination.title)
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