package com.example.kbway.userRoute.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kbway.R
import com.example.kbway.common.mvp.BaseFragment
import com.example.kbway.databinding.UserRouteBinding
import com.example.kbway.userMap.UserMapFragment
import com.example.kbway.userRoute.model.ButtonData
import com.example.kbway.userRoute.ui.adapter.RouteAdapter

class UserRouteFragment : BaseFragment(R.layout.user_route) {

    private lateinit var binding: UserRouteBinding

    private val userRecyclerAdapter: RouteAdapter by lazy {
        RouteAdapter(onClick = { showZoomedItem(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        buttonRecycler.layoutManager = LinearLayoutManager(requireContext())
        buttonRecycler.adapter = userRecyclerAdapter
    }

    private fun showZoomedItem(name: ButtonData) {
        val fragment = UserMapFragment()
        val bundle = Bundle()
        bundle.putParcelable("name", name)
        fragment.arguments = bundle
        changeFragment(fragment, R.id.contentContainer)
    }
}