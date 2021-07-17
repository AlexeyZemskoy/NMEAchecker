package com.example.nmeachecker

import gov.nasa.worldwind.geom.Angle
import gov.nasa.worldwind.geom.coords.UTMCoord
import java.util.concurrent.ConcurrentLinkedQueue

data class GpsDTO(val date: Long, val time: Long, val latitude: Double, val longitude: Double, val speed: Float, val direction: Float)

class NmeaCoordinatesFetchService: IFetchService {
    companion object {
        val queue = ConcurrentLinkedQueue<GpsDTO>()
    }
    private var isInit = true
    private var startHemisphere = 0
    private var startZone = 0
    private var prevPoint = Pair(0f, 0f)
    private val width = 106302.2f
    private val height = 317837.6f
    private var startOffset = Pair(0f, 0f)
    private fun translateHemisphere(hemisphereString: String) = hemisphereString.substringAfter("`avkey.").toCharArray()[0].toInt()

    override fun fetch(callback: (Any) -> Unit) {
        if(queue.isNotEmpty()) {
            val pointDTO = queue.poll()
            val utm = UTMCoord.fromLatLon(Angle.fromDegreesLatitude(pointDTO.latitude), Angle.fromDegreesLongitude(pointDTO.longitude))
            val translatedHemisphere = translateHemisphere(utm.hemisphere)
            if(isInit) {
                startHemisphere = translatedHemisphere
                startZone = utm.zone
                isInit = false
                startOffset = Pair(utm.easting.toFloat(), utm.northing.toFloat())
            }
            val newPoint = Pair(
                utm.easting.toFloat() - startOffset.first + (startZone - utm.zone) * width,
                utm.northing.toFloat() - startOffset.first + (startHemisphere - translatedHemisphere) * height
            )
            if(prevPoint != newPoint) {
                callback(newPoint.copy())
            }
            prevPoint = newPoint.copy()
        }
    }
}