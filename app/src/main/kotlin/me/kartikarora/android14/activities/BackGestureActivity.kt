package me.kartikarora.android14.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.screens.backgesture.BackGestureScreen
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.utils.SetupM3Scaffold

class BackGestureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.BackGesture) { paddingValues ->
                    BackGestureScreen(paddingValues)
                }
            }
        }
    }

    companion object {
        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, BackGestureActivity::class.java)
            activity.startActivity(intent)
        }
    }
}

@Preview
@Composable
fun BackLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.BackGesture) { paddingValues ->
            BackGestureScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun BackDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.BackGesture) { paddingValues ->
            BackGestureScreen(paddingValues)
        }
    }
}