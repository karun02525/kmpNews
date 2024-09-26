package org.example.project.di

import org.example.project.data.NewsApiServiceImpl
import org.example.project.domain.remote.NewsApiService
import org.example.project.presentation.NewsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule  = module {

    single<NewsApiService> {
        NewsApiServiceImpl()
    }

    factory {
        NewsViewModel(get())
    }
}

fun initKoin(){
    startKoin {
        modules(appModule)
    }
}