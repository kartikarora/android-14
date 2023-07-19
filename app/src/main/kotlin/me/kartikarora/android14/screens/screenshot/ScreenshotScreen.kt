package me.kartikarora.android14.screens.screenshot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.kartikarora.android14.R
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.composables.quantityStringZeroTwo
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.viewmodels.ScreenshotActivityViewModel

@Composable
fun ScreenshotScreen(
    paddingValues: PaddingValues,
    viewModel: ScreenshotActivityViewModel = viewModel()
) {
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
            val quantity = viewModel.screenShotCount
            val text = quantityStringZeroTwo(
                zeroResId = R.string.screenshot_count_string_zero,
                twoResId = R.string.screenshot_count_string_two,
                pluralResId = R.plurals.screenshot_count_string,
                quantity = quantity
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
            if (quantity >= 3) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.rma),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ScreenshotPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
            ScreenshotScreen(paddingValues)
        }
    }
}
