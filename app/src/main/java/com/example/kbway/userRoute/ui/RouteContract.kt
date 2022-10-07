package com.example.kbway.userRoute.ui

import com.example.kbway.common.mvp.BaseFragmentContract
import com.example.kbway.common.mvp.MvpPresenter
import com.example.kbway.common.mvp.MvpView
import com.example.kbway.userRoute.model.AllRouteData

interface RouteContract : BaseFragmentContract {
    interface View : MvpView {
        fun showRoutesList(data: List<AllRouteData.AllRouteDataItem?>)
    }

    interface Presenter : MvpPresenter<View> {
        fun getRoutesList()
    }
}