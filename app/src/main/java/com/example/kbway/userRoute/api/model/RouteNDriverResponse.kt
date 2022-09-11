package com.example.kbway.userRoute.api.model

import com.google.gson.annotations.SerializedName

data class RouteNDriverResponse(
    @SerializedName("id")
    val routeNDriverId: Int?,
    @SerializedName("driver")
    val routeNDriverDetailsResponse: RouteNDriverDetailsResponse?,
    @SerializedName("active")
    val routeNDriverActive: Boolean?
)
