package com.example.kbway.userRoute.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kbway.databinding.RouteButtonItemBinding
import com.example.kbway.userRoute.model.ButtonData

class RouteViewHolder(
    val binding: RouteButtonItemBinding,
    val onClick: (ButtonData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    constructor(
        parent: ViewGroup,
        onClick: (ButtonData) -> Unit
    ) : this(
        RouteButtonItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), onClick
    )


    fun bindItem(buttonData: ButtonData) {
        binding.routeTextView.text = buttonData.name
        binding.routeTextView.setOnClickListener {
            onClick.invoke(buttonData)
        }
    }
}