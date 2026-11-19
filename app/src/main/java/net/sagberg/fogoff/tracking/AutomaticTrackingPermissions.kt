package net.sagberg.fogoff.tracking

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

internal fun Context.hasRequiredAutomaticTrackingPermissions(): Boolean =
    hasAutomaticForegroundPermissions() && isGranted(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

internal fun Context.hasAutomaticForegroundPermissions(): Boolean =
    isGranted(Manifest.permission.ACCESS_FINE_LOCATION) &&
        isGranted(Manifest.permission.ACTIVITY_RECOGNITION)

private fun Context.isGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
