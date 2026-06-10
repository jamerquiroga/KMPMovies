package com.jquiroga.kmpmovies.data

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AndroidRegionDataSource(
    private val geocoder: Geocoder,
    private val fusedLocationClient: FusedLocationProviderClient
) : RegionDataSource {

    override suspend fun fetchRegion(): String {
        return fusedLocationClient.lastLocation()?.toRegion(geocoder) ?: DEFAULT_REGION
    }
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener {
            continuation.resume(it)
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}

private fun Location.toRegion(geocoder: Geocoder): String {
    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
    return addresses?.firstOrNull()?.countryCode ?: DEFAULT_REGION
}

