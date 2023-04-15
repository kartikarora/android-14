package me.kartikarora.android14.ui.composables

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource

@Composable
@ReadOnlyComposable
fun quantityStringZeroTwo(
    @StringRes zeroResId: Int,
    @StringRes twoResId: Int,
    @PluralsRes pluralResId: Int,
    quantity: Int,
    vararg formatArgs: Any
): String {
    return when (quantity) {
        0 -> stringResource(zeroResId)
        2 -> stringResource(twoResId)
        else -> pluralStringResource(pluralResId, quantity, quantity, formatArgs)
    }
}