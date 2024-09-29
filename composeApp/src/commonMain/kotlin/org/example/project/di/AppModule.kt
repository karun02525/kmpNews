package org.example.project.di

import org.example.project.data.NewsApiServiceImpl
import org.example.project.domain.remote.NewsApiService
import org.example.project.presentation.NewsViewModel
import org.example.project.presentation.home.viewmodel.DetailsViewModel
import org.example.project.presentation.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule  = module {

    single<NewsApiService> {
        NewsApiServiceImpl()
    }

    factory {
        NewsViewModel(get())
    }
    viewModel { HomeViewModel(get()) }

    viewModel { DetailsViewModel(get()) }
}

fun initKoin(){
    startKoin {
        modules(appModule)
    }
}