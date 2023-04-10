package me.kartikarora.android14.screens.grammar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.composables.ToggleButton
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.viewmodels.GrammaticalInflictionViewModel

@Composable
fun GrammarScreen(
    paddingValues: PaddingValues,
    viewModel: GrammaticalInflictionViewModel = viewModel(),
    word: String = "",
    sentence: String = "",
    onLanguageChange: () -> Unit = {},
    onGenderChange: () -> Unit = {}
) {

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
        ToggleButton(
            options = viewModel.languageOptions.map {
                val isSelected = it == viewModel.currentLanguage
                it.toToggleButtonOption(isSelected)
            }
        ) { options ->
            val selectedItem = options[0].text
            val selectedLanguage = viewModel.languageOptions.find { it.name == selectedItem }
                ?: viewModel.currentLanguage
            viewModel.updateLanguage(selectedLanguage)
            onLanguageChange.invoke()
        }
        Spacer(modifier = Modifier.height(8.dp))
        ToggleButton(
            options = viewModel.genderOptions.map {
                it.toToggleButtonOption(
                    it == viewModel.currentGender
                )
            }
        ) { options ->
            val selectedItem = options[0].text
            val selectedGender = viewModel.genderOptions.find { it.item == selectedItem }
                ?: viewModel.currentGender
            viewModel.updateGender(selectedGender)
            onGenderChange.invoke()
        }

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn {
            item {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Word"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    text = word,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Sentence"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    text = sentence
                )
            }
        }

    }
}

@Preview
@Composable
fun BackLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.GrammaticalInfliction) { paddingValues ->
            GrammarScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun BackDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.GrammaticalInfliction) { paddingValues ->
            GrammarScreen(paddingValues)
        }
    }
}