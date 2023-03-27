package me.kartikarora.android14.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.kartikarora.android14.screens.backgesture.BackGestureScreen
import me.kartikarora.android14.screens.home.HomeScreen
import me.kartikarora.android14.screens.mediapicker.PickerScreen
import java.io.Serializable

sealed class Destination(val title: String) : Serializable {
    object GrammaticalInfliction : Destination("Grammatical Infliction API")
    object PerAppLanguagePref : Destination("Per App Language Preference")
    object ScreenshotDetection : Destination("Screenshot Detection API")
    object SelectedPhotoAccess : Destination("Selected Photo Access")
    object BackGesture : Destination("Back Gesture Preview")
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
        composable(Destination.PerAppLanguagePref.title) { BackGestureScreen(paddingValues) }
        composable(Destination.GrammaticalInfliction.title) { BackGestureScreen(paddingValues) }
        composable(Destination.SelectedPhotoAccess.title) { PickerScreen(paddingValues) }
    }
}