package com.example.kbway.utils

import com.example.kbway.userRoute.model.ButtonData

object Utils {

    val userRouteButtons: ArrayList<ButtonData> = arrayListOf(
        ButtonData("Маршрут - 2"),
        ButtonData("Маршрут - 3"),
        ButtonData("Маршрут - 4"),
        ButtonData("Маршрут - 5"),
        ButtonData("Маршрут - 6"),
        ButtonData("Маршрут - 7"),
        ButtonData("Маршрут - 8"),
        ButtonData("Маршрут - 9")
    )

    const val BASE_URL = "http://188.225.44.132:5000/"

    var countActivityDestructions = 0

}