package me.kartikarora.android14.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.kartikarora.android14.nav.Destination

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_CURRENT_DESTINATION = "_KEY_CURRENT_DESTINATION"
    }

    private val shouldFetchContent = MutableLiveData(false)

    val imageUris = MutableLiveData<List<Uri?>>(null)

    var currentScreen by mutableStateOf<Destination>(
        savedStateHandle[KEY_CURRENT_DESTINATION] ?: Destination.Home
    )

    fun updateDestination(destination: Destination) {
        currentScreen = destination
        savedStateHandle[KEY_CURRENT_DESTINATION] = destination
    }

    fun getImageUrisFromContentResolver() {
        shouldFetchContent.value = true
    }

    fun setImageUris(imagesList: MutableList<Uri?>) {
        imageUris.value = imagesList
    }
}
