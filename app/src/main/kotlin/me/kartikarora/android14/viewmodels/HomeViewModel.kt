package me.kartikarora.android14.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.kartikarora.android14.nav.Destination

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_CURRENT_DESTINATION = "_KEY_CURRENT_DESTINATION"
        private const val KEY_PREV_DESTINATION = "_KEY_PREV_DESTINATION"
    }

    var prevScreen by mutableStateOf<Destination>(
        savedStateHandle[KEY_PREV_DESTINATION] ?: Destination.Home
    )


    var currentScreen by mutableStateOf<Destination>(
        savedStateHandle[KEY_CURRENT_DESTINATION] ?: Destination.Home
    )

    fun updateDestination(destination: Destination) {
        currentScreen = destination
        savedStateHandle[KEY_CURRENT_DESTINATION] = destination
    }

    fun updatePrevScreen(destination: Destination) {
        prevScreen = destination
        savedStateHandle[KEY_PREV_DESTINATION] = destination
    }
}
