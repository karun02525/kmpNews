package org.example.project.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.model.Meal
import org.example.project.domain.remote.NewsApiService
import org.example.project.utils.Response

class HomeViewModel(
    private val repository: NewsApiService
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {

        viewModelScope.launch {
            repository.fetchMeals().collect{result->
                when(result){
                    is Response.Loading ->{
                        _homeState.update { it.copy(isLoading =true) }
                    }
                    is Response.Success ->{
                        _homeState.update { it.copy(isLoading =false,
                            meals = result.data.meals
                            ) }
                    }
                    is Response.Error ->{
                        _homeState.update { it.copy(isLoading =false,
                            error = result.error?.message
                        ) }
                    }
                }
            }
        }
    }
}

data class HomeState(
    val meals:List<Meal> = emptyList(),
    val isLoading:Boolean = false,
    val error: String?=null
)