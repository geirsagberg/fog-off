package net.sagberg.fogoff.tracking

import net.sagberg.fogoff.storage.FogOffDatabase
import net.sagberg.fogoff.storage.AutomaticTrackingPreference
import net.sagberg.fogoff.storage.AutomaticTrackingPreferences

internal class DatabaseAutomaticTrackingGateway(
    private val database: FogOffDatabase,
) : AutomaticTrackingGateway {
    private val preferences = AutomaticTrackingPreferences(database.trackingSettings())
    private val recorder = AutomaticTrackingRecorder(database)

    override suspend fun preference(): AutomaticTrackingPreference = preferences.current()

    override suspend fun enable(automaticPeriodStartedAtMillis: Long) {
        preferences.enable(automaticPeriodStartedAtMillis)
    }

    override suspend fun disable() {
        preferences.disable()
    }

    override suspend fun recordingActive(): Boolean =
        database.recordings().active() != null

    override suspend fun record(
        fix: RecordingLocationFix,
        trigger: AutomaticFixTrigger,
        activity: RecordingActivity,
    ): RecordingLocationDecision = recorder.record(fix, trigger, activity)
}
