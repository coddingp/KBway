package com.example.kbway.userRoute.di

import com.example.kbway.userRoute.interactor.RouteInteractor
import com.example.kbway.userRoute.repository.RouteRemoteRepository
import com.example.kbway.userRoute.repository.RouteRepository
import com.example.kbway.userRoute.ui.RouteContract
import com.example.kbway.userRoute.ui.RoutePresenter
import org.koin.dsl.bind
import org.koin.dsl.module

object RouteModule {
    fun create() = module {
        factory {
            RoutePresenter(get())
        } bind RouteContract.Presenter::class

        single {
            RouteInteractor(get())
        } bind RouteInteractor::class

        single {
            RouteRemoteRepository(get())
        } bind RouteRepository::class
    }
}