package com.example.kbway.userRoute.api.model

import com.google.gson.annotations.SerializedName

data class RouteDriversResponse(
@SerializedName("id")
val routeId: Int?,
@SerializedName("lat")
val routeLat: Double?,
@SerializedName("lng")
val routeLng: Double?,
)
