package me.kartikarora.android14.viewmodels

import android.content.res.Configuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.kartikarora.android14.ui.composables.ToggleButtonOption
import java.io.Serializable

class GrammaticalInflictionViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_CURRENT_LANGUAGE = "_KEY_CURRENT_LANGUAGE"
        private const val KEY_CURRENT_GENDER = "_KEY_CURRENT_GENDER"
    }

    val languageOptions = listOf(
        Languages.English,
        Languages.French,
        Languages.Spanish
    )

    val genderOptions = listOf(
        Genders.Neutral,
        Genders.Masculine,
        Genders.Feminine
    )

    var currentLanguage by mutableStateOf<Languages>(
        savedStateHandle[KEY_CURRENT_LANGUAGE] ?: Languages.English
    )
    var currentGender by mutableStateOf<Genders>(
        savedStateHandle[KEY_CURRENT_GENDER] ?: Genders.Neutral
    )

    fun updateLanguage(language: Languages) {
        currentLanguage = language
        savedStateHandle[KEY_CURRENT_LANGUAGE] = language
    }

    fun updateGender(gender: Genders) {
        currentGender = gender
        savedStateHandle[KEY_CURRENT_GENDER] = gender
    }

    sealed class Languages(private val languageTag: String) : Serializable {

        object English : Languages("en-AU")
        object French : Languages("fr-FR")
        object Spanish : Languages("es-ES")

        val name: String
            get() {
                val locale = toLocaleList().get(0)
                return locale?.getDisplayLanguage(locale)
                    ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
                    ?: ""
            }

        fun toLocaleList(): LocaleListCompat {
            return LocaleListCompat.forLanguageTags(this.languageTag)
        }

        fun toToggleButtonOption(isCurrent: Boolean): ToggleButtonOption {
            return ToggleButtonOption(name, selected = isCurrent)
        }
    }

    sealed class Genders(val infliction: Int) : Serializable {
        object Masculine : Genders(Configuration.GRAMMATICAL_GENDER_MASCULINE)
        object Feminine : Genders(Configuration.GRAMMATICAL_GENDER_FEMININE)
        object Neutral : Genders(Configuration.GRAMMATICAL_GENDER_NEUTRAL)

        val item: String get() = this.javaClass.simpleName
        fun toToggleButtonOption(isCurrent: Boolean): ToggleButtonOption {
            return ToggleButtonOption(item, selected = isCurrent)
        }
    }
}