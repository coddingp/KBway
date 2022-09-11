package com.example.kbway.userRoute.api.model

import com.google.gson.annotations.SerializedName

data class RouteNDriverDetailsResponse(
@SerializedName("id")
val driverDetailsId: Int?,
@SerializedName("lat")
val driverDetailsLat: Double?,
@SerializedName("lng")
val driverDetailsLng: Double?,
)
