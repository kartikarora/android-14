# [Android 14](https://developer.android.com/about/versions/14)

Repository for an Android app built to demo the new developer centric features in Android 14. This project is in active development and contains beta and alpha features. 
Not everything is guaranteed to work. 

At the time of writing (15 April 2023) this app targets Android 14 Beta 1 and demonstrates usage of the following new APIs
- [Screenshot Detection API](https://developer.android.com/about/versions/14/features/screenshot-detection)
- [Selected Photo Access](https://developer.android.com/about/versions/14/changes/partial-photo-video-access)
- [Back Gesture Preview](https://developer.android.com/guide/navigation/predictive-back-gesture)
- [Grammatical Inflection API](https://developer.android.com/about/versions/14/features/grammatical-inflection)
- [Intent Chooser with Custom Actions](https://developer.android.com/about/versions/14/features#sharesheet-improvements)
- [Regional Preferences](https://developer.android.com/about/versions/14/features#regional-preferences)

## Building and Running

The project was developed using
- Android Studio Giraffe Canary 11. 
- AGP 8.1.0-alpha11
- Kotlin 1.8.0
- Java 17 VM
- Kotlin DSL
- Jetpack Compose
  
To build the app, clone the repository and open it as project in Android Studio Giraffe. Once indexed and dependencies are resolved, use `./gradlw assemble` to build an apk.
Spin up an emulator for Android UpsideDownCake Preview and install the app to check it out

Alternatively, if you are feeling adventurous, [Android 14 Beta 1](https://developer.android.com/about/versions/14/get) is available for Pixel devices. You can install [the latest apk](https://github.com/kartikarora/android-14/releases/latest) on a physical device to check it out.

## Contribution

Contributions are welcome. Project is under a GPT-3 license. Please open an issue before creating a pull request so that I have a chance to review the contribution to the best of my abilities.
There are no contribution guidelines other than that (it is a very small project).

## Support

If you want to support me with the work I do, feel free to [buy me a coffee](https://www.buymeacoffee.com/kartikarora)
