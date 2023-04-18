package me.kartikarora.android14.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.kartikarora.android14.nav.Destination


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