package com.jquiroga.kmpmovies.data

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.CLPlacemark
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume

class IosRegionDataSource : RegionDataSource {

    override suspend fun fetchRegion(): String {
        val geocoder = CLGeocoder()
        return getCurrentLocation()?.toRegion(geocoder) ?: DEFAULT_REGION
    }

    suspend fun getCurrentLocation(): CLLocation? {
        return suspendCancellableCoroutine { continuation ->
            val locationManager = CLLocationManager()
            locationManager.delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
                override fun locationManager(
                    manager: CLLocationManager,
                    didUpdateLocations: List<*>
                ) {
                    val location = didUpdateLocations.firstOrNull() as? CLLocation
                    locationManager.stopUpdatingLocation()
                    continuation.resume(location)
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didFailWithError: NSError
                ) {
                    locationManager.stopUpdatingLocation()
                    continuation.resume(null)
                }
            }
            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }
    }
}

private suspend fun CLLocation.toRegion(geocoder: CLGeocoder): String {
    return suspendCancellableCoroutine { continuation ->
        geocoder.reverseGeocodeLocation(this) { placemarks, error ->
            if (error != null || placemarks == null) {
                continuation.resume(DEFAULT_REGION)
            }
            val region = placemarks?.firstOrNull()?.let {
                (it as CLPlacemark).ISOcountryCode
            } ?: DEFAULT_REGION
            continuation.resume(region)
        }
    }
}
