package net.sagberg.fogoff

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import net.sagberg.fogoff.storage.FogOffDatabase
import net.sagberg.fogoff.ui.RecordingBorder
import net.sagberg.fogoff.settings.CoverageOutlineStyle
import net.sagberg.fogoff.diagnostics.TrackingDiagnosticsRoute
import net.sagberg.fogoff.map.MapScreen
import net.sagberg.fogoff.map.MapMenuActions
import net.sagberg.fogoff.settings.AppearanceDialog
import net.sagberg.fogoff.settings.AppearancePreferences
import net.sagberg.fogoff.settings.CoverageSettingsRoute
import net.sagberg.fogoff.settings.ThemeMode
import net.sagberg.fogoff.ui.theme.FogOffTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FogOffApp()
        }
    }
}

internal enum class FogOffDestination { MAP, SETTINGS, TRACKING_DIAGNOSTICS }

@Composable
internal fun FogOffApp() {
    var destination by rememberSaveable { mutableStateOf(FogOffDestination.MAP) }
    var appearanceOpen by rememberSaveable { mutableStateOf(false) }
    val settingsPersistenceScope = rememberCoroutineScope()
    val context = LocalContext.current
    val appearancePreferences = remember(context) { AppearancePreferences(context) }
    var appearance by remember(appearancePreferences) {
        mutableStateOf(appearancePreferences.load())
    }
    val darkTheme = when (appearance.themeMode) {
        ThemeMode.System -> isSystemInDarkTheme()
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }
    SystemBarsFollowTheme(darkTheme)

    FogOffTheme(darkTheme = darkTheme) {
        Box(Modifier.fillMaxSize()) {
            when (destination) {
                FogOffDestination.MAP -> MapScreen(
                    coverageOutline = appearance.coverageOutline,
                    fogDetail = appearance.fogDetail,
                    menuActions = MapMenuActions(
                        onOpenSettings = { destination = FogOffDestination.SETTINGS },
                        onOpenAppearance = { appearanceOpen = true },
                        onOpenTrackingDiagnostics = {
                            destination = FogOffDestination.TRACKING_DIAGNOSTICS
                        },
                    ),
                )
                FogOffDestination.SETTINGS -> {
                    BackHandler { destination = FogOffDestination.MAP }
                    CoverageSettingsRoute(
                        persistenceScope = settingsPersistenceScope,
                        onBack = { destination = FogOffDestination.MAP },
                    )
                }
                FogOffDestination.TRACKING_DIAGNOSTICS -> {
                    BackHandler { destination = FogOffDestination.MAP }
                    TrackingDiagnosticsRoute(onBack = { destination = FogOffDestination.MAP })
                }
            }
            if (destination != FogOffDestination.MAP) {
                SecondaryScreenRecordingBorder(appearance.coverageOutline)
            }
        }
        if (appearanceOpen) {
            AppearanceDialog(
                appearance = appearance,
                onAppearanceChange = {
                    appearance = it
                    appearancePreferences.save(it)
                },
                onDismiss = { appearanceOpen = false },
            )
        }
    }
}

/** Keeps status and navigation bar icons legible when the theme differs from the system's. */
@Composable
private fun SystemBarsFollowTheme(darkTheme: Boolean) {
    val activity = LocalActivity.current as? ComponentActivity ?: return
    SideEffect {
        val style = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { darkTheme }
        activity.enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
    }
}

/** Only secondary screens observe here; the Map owns its subscription and restore lifecycle. */
@Composable
private fun SecondaryScreenRecordingBorder(coverageOutline: CoverageOutlineStyle) {
    val context = LocalContext.current
    val database = remember(context) { FogOffDatabase.open(context) }
    val activeRecording by database.recordings().observeActive().collectAsState(initial = null)
    RecordingBorder(activeRecording != null, coverageOutline)
}
