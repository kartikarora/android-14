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
    }

    var currentScreen by mutableStateOf<Destination>(
        savedStateHandle[KEY_CURRENT_DESTINATION] ?: Destination.Home
    )

    fun updateDestination(destination: Destination) {
        currentScreen = destination
        savedStateHandle[KEY_CURRENT_DESTINATION] = destination
    }
}
