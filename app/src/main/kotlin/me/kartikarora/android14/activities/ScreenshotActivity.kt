package me.kartikarora.android14.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import me.kartikarora.android14.R
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.screens.screenshot.ScreenshotScreen
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.utils.SetupM3Scaffold
import me.kartikarora.android14.viewmodels.ScreenshotActivityViewModel

class ScreenshotActivity : ComponentActivity(), Activity.ScreenCaptureCallback {

    private val viewModel: ScreenshotActivityViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
                    Log.i("Screenshot count", viewModel.screenShotCount.toString())
                    ScreenshotScreen(
                        paddingValues,
                        pluralStringResource(
                            R.plurals.screenshot_count_string,
                            viewModel.screenShotCount,
                            viewModel.screenShotCount
                        )
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerScreenCaptureCallback(mainExecutor, this)
    }

    override fun onStop() {
        super.onStop()
        unregisterScreenCaptureCallback(this)
    }

    override fun onScreenCaptured() {
        viewModel.onScreensCaptured()
    }

    companion object {
        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, ScreenshotActivity::class.java)
            activity.startActivity(intent)
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
