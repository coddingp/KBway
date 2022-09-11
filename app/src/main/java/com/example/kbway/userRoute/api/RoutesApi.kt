package com.example.kbway.userRoute.api

import com.example.kbway.userRoute.api.model.RoutesDataResponse
import com.example.kbway.utils.Utils.ROUTES_ENDPOINT
import retrofit2.http.GET

interface RoutesApi {
    @GET(ROUTES_ENDPOINT)
    suspend fun getRouteData(
    ): List<RoutesDataResponse>
}