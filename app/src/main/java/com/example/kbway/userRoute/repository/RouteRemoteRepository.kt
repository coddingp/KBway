package com.example.kbway.userRoute.repository

import com.example.kbway.userRoute.api.RoutesApi
import com.example.kbway.userRoute.model.AllRouteData
import com.example.kbway.userRoute.model.RouteDataConverter
import timber.log.Timber

class RouteRemoteRepository(
    private val api: RoutesApi
) : RouteRepository {
    override suspend fun getRouteData(): List<AllRouteData.AllRouteDataItem?> {
        val routeData = api.getRouteData()
        Timber.tag("%RouteRemoteRepository").e("getRouteData val routeData = $routeData")
        return RouteDataConverter.fromNetWork(routeData)
    }
}