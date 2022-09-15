package com.example.kbway.userRoute.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kbway.R
import com.example.kbway.common.mvp.BaseFragmentMvp
import com.example.kbway.databinding.UserRouteBinding
import com.example.kbway.userMap.UserMapFragment
import com.example.kbway.userRoute.model.AllRouteData
import com.example.kbway.userRoute.ui.adapter.RouteAdapter
import org.koin.android.ext.android.inject

class UserRouteFragment :
    BaseFragmentMvp<RouteContract.View, RouteContract.Presenter>(R.layout.user_route),
    RouteContract.View {

    private lateinit var binding: UserRouteBinding

    override val presenter: RoutePresenter by inject()

    private val userRecyclerAdapter: RouteAdapter by lazy {
        RouteAdapter(onClick = { showItemMap(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getRoutesList()
        buttonRecycler.layoutManager = LinearLayoutManager(requireContext())
        buttonRecycler.adapter = userRecyclerAdapter
    }

    private fun showItemMap(routeName: AllRouteData.AllRouteDataItem) {
        val fragment = UserMapFragment()
        val bundle = Bundle()
        bundle.putParcelable("name", routeName)
        fragment.arguments = bundle
        changeFragment(fragment, R.id.contentContainer)
    }

    override fun showRoutesList(data: List<AllRouteData.AllRouteDataItem?>) {
        userRecyclerAdapter.setData(data)
    }
}