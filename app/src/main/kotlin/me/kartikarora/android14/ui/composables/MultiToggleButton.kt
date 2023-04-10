package me.kartikarora.android14.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectionPill(
    option: ToggleButtonOption,
    onClick: (option: ToggleButtonOption) -> Unit = {}
) {
    val colors = if (option.selected) {
        ButtonDefaults.filledTonalButtonColors()
    } else {
        ButtonDefaults.textButtonColors()
    }
    FilledTonalButton(
        colors = colors,
        onClick = { onClick(option) }
    ) {
        if (option.iconRes != null) {
            Icon(
                imageVector = option.iconRes,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(width = 4.dp))
        }
        Text(
            modifier = Modifier.wrapContentSize(),
            text = option.text
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToggleButton(
    options: List<ToggleButtonOption>,
    modifier: Modifier = Modifier,
    type: SelectionType = SelectionType.Single,
    onClick: (selectedOptions: List<ToggleButtonOption>) -> Unit = {},
) {
    val state = remember { mutableStateMapOf<String, ToggleButtonOption>() }

    OutlinedCard(
        onClick = { },
        modifier = modifier
            .wrapContentSize()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            if (options.isEmpty()) {
                return@OutlinedCard
            }
            state.clear()
            options.filter { it.selected }
                .forEach {
                    state[it.text] = it
                }
            val onItemClick: (option: ToggleButtonOption) -> Unit = { option ->
                if (type == SelectionType.Single) {
                    options.forEach {
                        val key = it.text
                        if (key == option.text) {
                            state[key] = option
                        } else {
                            state.remove(key)
                        }
                    }
                } else {
                    val key = option.text
                    if (!state.contains(key)) {
                        state[key] = option
                    } else {
                        state.remove(key)
                    }
                }
                onClick(state.values.toList())
            }
            if (options.size == 1) {
                val option = options.first().apply {
                    selected = state.contains(text)
                }
                SelectionPill(
                    option = option,
                    onClick = onItemClick,
                )
                return@OutlinedCard
            }
            val first = options.first().apply {
                selected = state.contains(text)
            }
            val last = options.last().apply {
                selected = state.contains(text)
            }
            val middle = options.slice(1..options.size - 2)

            SelectionPill(
                option = first,
                onClick = onItemClick,
            )
            middle.map { option ->
                option.selected = state.contains(option.text)
                SelectionPill(
                    option = option,
                    onClick = onItemClick,
                )
            }
            SelectionPill(
                option = last,
                onClick = onItemClick,
            )
        }
    }
}

sealed class SelectionType {
    object None : SelectionType()
    object Single : SelectionType()
    object Multiple : SelectionType()
}

data class ToggleButtonOption(
    val text: String,
    val iconRes: ImageVector? = null,
    var selected: Boolean = false
)

@Preview
@Composable
fun ToggleButtonPreview() {
    ToggleButton(
        options = listOf(
            ToggleButtonOption("one"),
            ToggleButtonOption("two", selected = true),
            ToggleButtonOption("three", Icons.Default.CheckCircle),
            ToggleButtonOption("four", Icons.Default.CheckCircle, selected = true)
        )
    )
}
