package com.example.kbway.userRoute.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kbway.userRoute.model.ButtonData
import com.example.kbway.utils.Utils.userRouteButtons

class RouteAdapter(
    val onClick: (ButtonData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = userRouteButtons

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RouteViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RouteViewHolder -> {
                holder.bindItem(data[position])
            }
        }
    }

    override fun getItemCount() = data.size

}