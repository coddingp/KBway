package com.example.kbway.userRoute.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kbway.userRoute.model.AllRouteData
import timber.log.Timber

class RouteAdapter(
    val onClick: (AllRouteData.AllRouteDataItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listOfData = mutableListOf<AllRouteData.AllRouteDataItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RouteViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RouteViewHolder -> {
                listOfData[position]?.let { holder.bindItem(it) }
            }
        }
    }

    override fun getItemCount() = listOfData.size

    fun setData(data: List<AllRouteData.AllRouteDataItem?>) {
        listOfData.clear()
        listOfData.addAll(data)
        notifyDataSetChanged()
    }
}