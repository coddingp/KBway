package com.example.kbway.userRoute.api.model

import com.google.gson.annotations.SerializedName

data class RoutesDataResponse(
    @SerializedName("id")
    val routeIdResponse: Int?,
    @SerializedName("coordinates")
    val routeCoordinatesResponse: List<List<Double>>?,
    @SerializedName("number")
    val routeNumberResponse: Int?,
    @SerializedName("drivers")
    val routeDriverResponses: List<RouteDriversResponse>?,
    @SerializedName("routendriver")
    val routeNDriverResponse: List<RouteNDriverResponse>?
)
