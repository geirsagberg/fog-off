package net.sagberg.fogoff.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import net.sagberg.fogoff.R

internal val LocalDarkTheme = staticCompositionLocalOf { false }

private val LightColors = lightColorScheme(
    primary = Color(0xFF006B57),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF8EF8D5),
    onPrimaryContainer = Color(0xFF002018),
    secondary = Color(0xFF4B635B),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCEE9DD),
    onSecondaryContainer = Color(0xFF082019),
    tertiary = Color(0xFF805500),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDDB5),
    onTertiaryContainer = Color(0xFF291800),
    background = Color(0xFFF5FBF7),
    onBackground = Color(0xFF171D1A),
    surface = Color(0xFFF5FBF7),
    onSurface = Color(0xFF171D1A),
    surfaceVariant = Color(0xFFDBE5DF),
    onSurfaceVariant = Color(0xFF404944),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFEFF5F1),
    surfaceContainer = Color(0xFFE9EFEB),
    surfaceContainerHigh = Color(0xFFE4EAE6),
    surfaceContainerHighest = Color(0xFFDEE4E0),
    outline = Color(0xFF707974),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF72DBBC),
    onPrimary = Color(0xFF00382B),
    primaryContainer = Color(0xFF005140),
    onPrimaryContainer = Color(0xFF8EF8D5),
    secondary = Color(0xFFB3CCC1),
    onSecondary = Color(0xFF1F352E),
    secondaryContainer = Color(0xFF354B43),
    onSecondaryContainer = Color(0xFFCEE9DD),
    tertiary = Color(0xFFF8BD6B),
    onTertiary = Color(0xFF462B00),
    tertiaryContainer = Color(0xFF633F00),
    onTertiaryContainer = Color(0xFFFFDDB5),
    background = Color(0xFF0E1512),
    onBackground = Color(0xFFDEE5E0),
    surface = Color(0xFF0E1512),
    onSurface = Color(0xFFDEE5E0),
    surfaceVariant = Color(0xFF404944),
    onSurfaceVariant = Color(0xFFBFC9C3),
    surfaceContainerLowest = Color(0xFF090F0D),
    surfaceContainerLow = Color(0xFF171D1A),
    surfaceContainer = Color(0xFF1B211E),
    surfaceContainerHigh = Color(0xFF252B29),
    surfaceContainerHighest = Color(0xFF303634),
    outline = Color(0xFF89938D),
)

// Google Sans Flex from github.com/google/fonts (ofl/googlesansflex), licensed under the SIL Open
// Font License in app/licenses/. Only weight 400–600 varies; unused axes are pinned to defaults.
@OptIn(ExperimentalTextApi::class)
private val GoogleSansFlex = FontFamily(
    listOf(FontWeight.Normal, FontWeight.Medium, FontWeight.SemiBold).map { weight ->
        Font(
            resId = R.font.google_sans_flex,
            weight = weight,
            variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)),
        )
    },
)

private val FogOffFont = TextStyle(fontFamily = GoogleSansFlex)

private val FogOffTypography = Typography().run {
    Typography(
        displayLarge = displayLarge.merge(FogOffFont),
        displayMedium = displayMedium.merge(FogOffFont),
        displaySmall = displaySmall.merge(FogOffFont),
        headlineLarge = headlineLarge.merge(FogOffFont),
        headlineMedium = headlineMedium.merge(FogOffFont),
        headlineSmall = headlineSmall.merge(FogOffFont),
        titleLarge = titleLarge.merge(FogOffFont),
        titleMedium = titleMedium.merge(FogOffFont),
        titleSmall = titleSmall.merge(FogOffFont),
        bodyLarge = bodyLarge.merge(FogOffFont),
        bodyMedium = bodyMedium.merge(FogOffFont),
        bodySmall = bodySmall.merge(FogOffFont),
        labelLarge = labelLarge.merge(FogOffFont),
        labelMedium = labelMedium.merge(FogOffFont),
        labelSmall = labelSmall.merge(FogOffFont),
    )
}

@Composable
fun FogOffTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalDarkTheme provides darkTheme) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColors else LightColors,
            typography = FogOffTypography,
            content = content,
        )
    }
}
