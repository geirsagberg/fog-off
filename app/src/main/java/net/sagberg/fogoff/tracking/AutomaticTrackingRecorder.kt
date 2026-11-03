package net.sagberg.fogoff.tracking

import net.sagberg.fogoff.coverage.CoverageEvidenceRecorder
import net.sagberg.fogoff.coverage.CoverageEvidenceRules
import net.sagberg.fogoff.coverage.CoverageLocationDecision
import net.sagberg.fogoff.coverage.ShortGapInterpolationPolicy
import net.sagberg.fogoff.storage.CoverageEvidenceSource
import net.sagberg.fogoff.storage.FogOffDatabase

internal const val AUTOMATIC_ACCURACY_REJECTION = "accuracy_exceeds_automatic_limit"
internal const val MAXIMUM_AUTOMATIC_INTERPOLATION_GAP_MILLIS = 120_000L

internal enum class AutomaticFixTrigger(val persistedName: String) {
    INITIAL_ENABLE("initial_enable"),
    MOVEMENT_WINDOW("movement_window"),
    FALLBACK_WINDOW("fallback_window"),
    OPPORTUNISTIC("opportunistic"),
}

internal class AutomaticTrackingRecorder(
    database: FogOffDatabase,
) {
    private val evidenceRecorder = CoverageEvidenceRecorder(database)
    private val coverageSettings = database.coverageSettingsRepository

    suspend fun record(
        fix: RecordingLocationFix,
        trigger: AutomaticFixTrigger,
        activity: RecordingActivity,
    ): CoverageLocationDecision {
        val settings = coverageSettings.current()
        return evidenceRecorder.record(
            fix = fix,
            rules = CoverageEvidenceRules(
                source = CoverageEvidenceSource.AUTOMATIC_TRACKING,
                trigger = trigger.persistedName,
                maximumAccuracyMeters = settings.maximumAcceptedAccuracyMeters.toDouble(),
                maximumInterpolationDistanceSteps = settings.maximumInterpolationDistanceSteps,
                accuracyRejectionReason = AUTOMATIC_ACCURACY_REJECTION,
                shortGapInterpolation = ShortGapInterpolationPolicy(
                    maximumGapMillis = MAXIMUM_AUTOMATIC_INTERPOLATION_GAP_MILLIS,
                    eligiblePreviousTriggers = AutomaticFixTrigger.entries
                        .mapTo(mutableSetOf(), AutomaticFixTrigger::persistedName),
                ),
            ),
            activityMode = activity.persistedMode,
        )
    }
}
