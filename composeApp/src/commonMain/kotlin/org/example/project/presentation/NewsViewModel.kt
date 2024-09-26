package org.example.project.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.example.project.domain.model.Articles
import org.example.project.domain.remote.NewsApiService
import org.example.project.domain.remote.RequestCondition

class NewsViewModel(
    private val rep:NewsApiService
) : ScreenModel{

    private val _allNews:MutableState<RequestCondition<List<Articles?>>> = mutableStateOf(RequestCondition.IdleCondition)
    val allNews: State<RequestCondition<List<Articles?>>> = _allNews


    init {
        retrieveNews()
    }

    private fun retrieveNews(){
        screenModelScope.launch {
            rep.readTopHeadLine().let { it ->
                it.collectLatest {
                    _allNews.value = it
                }
            }
        }
    }

}