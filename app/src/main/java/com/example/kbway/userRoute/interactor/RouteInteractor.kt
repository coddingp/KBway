package com.example.kbway.userRoute.interactor

import com.example.kbway.userRoute.model.AllRouteData
import com.example.kbway.userRoute.repository.RouteRemoteRepository

class RouteInteractor(
    private val remoteRepository: RouteRemoteRepository
) {
    suspend fun getRouteData(): List<AllRouteData.AllRouteDataItem?> =
        remoteRepository.getRouteData()
}