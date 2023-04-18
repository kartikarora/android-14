package me.kartikarora.android14.screens.chooser

import android.app.PendingIntent
import android.app.SearchManager
import android.content.Intent
import android.graphics.drawable.Icon
import android.service.chooser.ChooserAction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kartikarora.android14.R
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.theme.Android14Theme

@Composable
fun IntentChooserScreen(
    paddingValues: PaddingValues
) {
    var openChooser by remember { mutableStateOf(false) }
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
        if (openChooser) {
            with(LocalContext.current) {
                val chooserIntent = Intent.createChooser(
                    Intent(Intent.ACTION_SEND)
                        .setType(stringResource(R.string.image_mimetype))
                        .putExtra(Intent.EXTRA_TEXT, stringResource(R.string.intent_chooser_extra)),
                    stringResource(R.string.intent_chooser_title)
                ).apply {
                    val customAction = ChooserAction.Builder(
                        Icon.createWithResource(this@with, R.drawable.baseline_android),
                        stringResource(id = R.string.app_name),
                        PendingIntent.getActivity(
                            this@with,
                            1234,
                            Intent(Intent.ACTION_WEB_SEARCH).apply {
                                putExtra(
                                    SearchManager.QUERY,
                                    stringResource(R.string.custom_action_intent_query)
                                )
                            },
                            PendingIntent.FLAG_IMMUTABLE
                        )
                    ).build()
                    this.putExtra(Intent.EXTRA_CHOOSER_CUSTOM_ACTIONS, arrayOf(customAction))
                }
                startActivity(chooserIntent)
                openChooser = false
            }
        }
        Button(
            onClick = {
                openChooser = true
            }
        ) {
            Text(text = stringResource(R.string.share_an_image_button_title))
        }
    }
}

@Preview
@Composable
fun BackLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.CustomActionIntentChooser) { paddingValues ->
            IntentChooserScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun BackDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.CustomActionIntentChooser) { paddingValues ->
            IntentChooserScreen(paddingValues)
        }
    }
}