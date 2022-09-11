package com.example.kbway.userRoute.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class AllRouteData : Parcelable {

    @Parcelize
    data class AllRouteDataItem(
        val routeId: Int?,
        val routeCoordinates: List<List<Double>>?,
        val routeNumber: Int?,
        val routeDriver: List<RouteDrivers>?,
        val routeNDriver: List<RoutenDriver>?
    ) : AllRouteData()

    @Parcelize
    data class RouteDrivers(
        val IdrouteDrivers: Int?,
        val latRouteDrivers: Double?,
        val lngRouteDrivers: Double?
    ) : AllRouteData()

    @Parcelize
    data class RoutenDriver(
        val routenDriverActive: Boolean?,
        val routenDriverDriver: DriverX?,
        val routenDriverId: Int?
    ) : AllRouteData()

    @Parcelize
    data class DriverX(
        val IdDriverX: Int?,
        val latDriverX: Double?,
        val lngDriverX: Double?
    ) : AllRouteData()
}