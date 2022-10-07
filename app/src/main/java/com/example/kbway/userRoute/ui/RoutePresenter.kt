package com.example.kbway.userRoute.ui

import com.example.kbway.common.mvp.BasePresenter
import com.example.kbway.userRoute.interactor.RouteInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class RoutePresenter(
    private val interactor: RouteInteractor
) : BasePresenter<RouteContract.View>(),
    RouteContract.Presenter {

    private val presenterScope = CoroutineScope(Dispatchers.Main.immediate)

    override fun getRoutesList() {
        presenterScope.launch {
            try {
                val routes = interactor.getRouteData()
                Timber.tag("%RoutePresenter getRoutesList()").i("data:  $routes")
                view?.showRoutesList(routes)
            } catch (t: Throwable) {
//                Timber.tag("%getRoutesList in catch blcok").e("Error route data -> ${t.message}")
            }
        }
    }
}