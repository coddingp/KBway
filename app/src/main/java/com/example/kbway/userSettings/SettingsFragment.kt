package com.example.kbway.userSettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kbway.R
import com.example.kbway.common.mvp.BaseFragment
import com.example.kbway.databinding.SettingsBinding
import com.example.kbway.databinding.UserRouteBinding
import com.example.kbway.userMap.UserMapFragment
import com.example.kbway.userRoute.model.ButtonData
import com.example.kbway.userRoute.ui.adapter.RouteAdapter

class SettingsFragment : BaseFragment(R.layout.user_route) {

    private lateinit var binding: SettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
    }
}