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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.kartikarora.android14.R
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.composables.ToggleButton
import me.kartikarora.android14.ui.theme.Android14Theme
import me.kartikarora.android14.viewmodels.GrammaticalInflectionViewModel

@Composable
fun GrammarScreen(
    paddingValues: PaddingValues,
    viewModel: GrammaticalInflectionViewModel = viewModel(),
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
                    text = stringResource(R.string.grammar_api_word_title)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    text = word,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(R.string.grammar_api_sentence_title)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    text = sentence
                )
            }
        }

    }
}

@PreviewLightDark
@Composable
fun GrammarPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.GrammaticalInflection) { paddingValues ->
            GrammarScreen(paddingValues)
        }
    }
}