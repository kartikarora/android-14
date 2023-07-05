package me.kartikarora.android14.screens.regionalprefs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.util.LocalePreferences
import me.kartikarora.android14.R
import me.kartikarora.android14.nav.Destination
import me.kartikarora.android14.ui.composables.SetupM3Scaffold
import me.kartikarora.android14.ui.theme.Android14Theme
import java.util.Locale

@Composable
fun RegionalPrefsScreen(
    paddingValues: PaddingValues
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
            // First day of the week
            val firstDayOfWeek = stringResource(
                R.string.regional_pref_day_suffix,
                LocalePreferences.getFirstDayOfWeek()
            )
            Text(
                text = stringResource(R.string.regional_pref_first_day_of_week_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = firstDayOfWeek.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Temperature Unit
            val tempUnit = LocalePreferences.getTemperatureUnit()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            Text(
                text = stringResource(R.string.regional_pref_temperature_unit_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = tempUnit,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Hours Cycle
            val hourCycle = LocalePreferences.getHourCycle()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            Text(
                text = stringResource(R.string.regional_pref_hour_cycle_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = hourCycle,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Calendar Type
            val calendarType = LocalePreferences.getCalendarType()
            Text(
                text = stringResource(R.string.regional_pref_calendar_type_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = calendarType.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun BackLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.RegionalPrefs) { paddingValues ->
            RegionalPrefsScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun BackDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.RegionalPrefs) { paddingValues ->
            RegionalPrefsScreen(paddingValues)
        }
    }
}