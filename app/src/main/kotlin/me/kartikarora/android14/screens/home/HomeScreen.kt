package me.kartikarora.android14.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import me.kartikarora.android14.R
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.ButtonForDemoOf
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
            Text(
                text = stringResource(id = R.string.core_theme_privacy_security),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            ButtonForDemoOf(Destination.ScreenshotDetection, onClick)
            ButtonForDemoOf(Destination.SelectedPhotoAccess, onClick)
            Text(
                text = stringResource(id = R.string.core_theme_system_ui),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            ButtonForDemoOf(Destination.BackGesture, onClick)
            ButtonForDemoOf(Destination.CustomActionIntentChooser, onClick)
            Text(
                text = stringResource(id = R.string.core_theme_personalisation),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            ButtonForDemoOf(Destination.GrammaticalInflection, onClick)
            ButtonForDemoOf(Destination.RegionalPrefs, onClick)
        }
    }
}

@PreviewLightDark
@Composable
fun HomePreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.Home) { paddingValues ->
            HomeScreen(paddingValues)
        }
    }
}