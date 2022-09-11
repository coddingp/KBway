package com.example.kbway.userRoute.model

import com.example.kbway.userRoute.api.model.RouteDriversResponse
import com.example.kbway.userRoute.api.model.RouteNDriverDetailsResponse
import com.example.kbway.userRoute.api.model.RouteNDriverResponse
import com.example.kbway.userRoute.api.model.RoutesDataResponse

object RouteDataConverter {


    fun fromNetWork(response: List<RoutesDataResponse>) =
        response.map {
            AllRouteData.AllRouteDataItem(
                routeId = it.routeIdResponse,
                routeCoordinates = it.routeCoordinatesResponse,
                routeNumber = it.routeNumberResponse,
                routeDriver = it.routeDriverResponses?.let { it1 -> fromNetWorkRouteDrivers(it1) },
                routeNDriver = it.routeNDriverResponse?.let { it1 -> fromNetWorkRouteDetails(it1) }
            )
        }

    private fun fromNetWorkRouteDrivers(response: List<RouteDriversResponse>) =
        response.map {
            AllRouteData.RouteDrivers(
                IdrouteDrivers = it.routeId,
                latRouteDrivers = it.routeLat,
                lngRouteDrivers = it.routeLng
            )
        }

    private fun fromNetWorkRouteDetails(response: List<RouteNDriverResponse>) =
        response.map {
            AllRouteData.RoutenDriver(
                routenDriverActive = it.routeNDriverActive,
                routenDriverDriver = it.routeNDriverDetailsResponse?.let { it1 ->
                    fromNetWorkRouteDetails(it1)
                },
                routenDriverId = it.routeNDriverId
            )
        }

    private fun fromNetWorkRouteDetails(response: RouteNDriverDetailsResponse) =
        AllRouteData.DriverX(
            IdDriverX = response.driverDetailsId,
            latDriverX = response.driverDetailsLat,
            lngDriverX = response.driverDetailsLng
        )


}