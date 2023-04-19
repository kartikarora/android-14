package me.kartikarora.android14.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.kartikarora.android14.screens.chooser.IntentChooserScreen
import me.kartikarora.android14.screens.home.HomeScreen
import me.kartikarora.android14.screens.mediapicker.PickerScreen
import me.kartikarora.android14.screens.regionalprefs.RegionalPrefsScreen
import java.io.Serializable

sealed class Destination(val title: String) : Serializable {
    object GrammaticalInflection : Destination("Grammatical Inflection API")
    object ScreenshotDetection : Destination("Screenshot Detection API")
    object SelectedPhotoAccess : Destination("Selected Photo Access")
    object BackGesture : Destination("Back Gesture Preview")
    object CustomActionIntentChooser : Destination("Custom Action in Intent Chooser")
    object RegionalPrefs : Destination("Regional Preferences")
    object Home : Destination("Android 14")
}

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    navigateTo: (Destination) -> Unit
) {
    NavHost(navController = navHostController, startDestination = Destination.Home.title) {
        composable(Destination.Home.title) { HomeScreen(paddingValues) { navigateTo.invoke(it) } }
        composable(Destination.SelectedPhotoAccess.title) { PickerScreen(paddingValues) }
        composable(Destination.CustomActionIntentChooser.title) { IntentChooserScreen(paddingValues) }
        composable(Destination.RegionalPrefs.title) { RegionalPrefsScreen(paddingValues) }
    }
}