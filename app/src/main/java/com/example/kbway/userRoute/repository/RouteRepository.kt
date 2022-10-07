package com.example.kbway.userRoute.repository

import com.example.kbway.userRoute.model.AllRouteData

interface RouteRepository {
    suspend fun getRouteData(): List<AllRouteData.AllRouteDataItem?>
}