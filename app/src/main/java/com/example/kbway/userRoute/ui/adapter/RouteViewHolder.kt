package com.example.kbway.userRoute.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kbway.databinding.RouteButtonItemBinding
import com.example.kbway.userRoute.model.AllRouteData

class RouteViewHolder(
    val binding: RouteButtonItemBinding,
    val onClick: (AllRouteData.AllRouteDataItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        onClick: (AllRouteData.AllRouteDataItem) -> Unit
    ) : this(
        RouteButtonItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), onClick
    )


    fun bindItem(buttonData: AllRouteData.AllRouteDataItem) {
        binding.routeTextView.text = "Маршрут - ${buttonData.routeNumber}"
        binding.routeTextView.setOnClickListener {
            onClick.invoke(buttonData)
        }
    }
}