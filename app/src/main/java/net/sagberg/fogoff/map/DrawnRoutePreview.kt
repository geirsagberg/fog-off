package net.sagberg.fogoff.map

import net.sagberg.fogoff.coverage.CoverageCellId
import net.sagberg.fogoff.coverage.GeoCoordinate

internal data class DrawnRoutePreview(
    val waypoints: List<GeoCoordinate> = emptyList(),
    val cells: Set<CoverageCellId> = emptySet(),
    val failingSegmentIndex: Int? = null,
) {
    val canConfirm: Boolean get() = waypoints.size >= 2 && failingSegmentIndex == null
}

internal data class DrawnRouteMapInteractions(
    val active: Boolean,
    val preview: DrawnRoutePreview,
    val onMapClick: (GeoCoordinate) -> Unit,
    val onMoveWaypoint: (Int, GeoCoordinate) -> Unit,
    val editable: Boolean = active,
)
