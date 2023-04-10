package me.kartikarora.android14.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ScreenshotActivityViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val KEY_SCREENSHOT_COUNT = "_KEY_SCREENSHOT_COUNT"
    }

    var screenShotCount by mutableStateOf(savedStateHandle[KEY_SCREENSHOT_COUNT] ?: 0)

    fun onScreensCaptured() {
        savedStateHandle[KEY_SCREENSHOT_COUNT] = ++screenShotCount
    }

}
