package org.example.project.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.model.MealX
import org.example.project.domain.remote.NewsApiService
import org.example.project.utils.Response

class DetailsViewModel(
    private val repository : NewsApiService
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailsState())
    val detailState = _detailState.asStateFlow()


    fun fetchMealById(id:String) {
        viewModelScope.launch {
            repository.fetchMealById(id).collect { result ->
                when (result) {

                    is Response.Loading -> {
                        _detailState.update { it.copy(loading = true) }
                    }

                    is Response.Success -> {
                        _detailState.update {
                            it.copy(
                                loading = false,
                                error = null,
                                meals = result.data.meals.first()
                            )
                        }
                    }

                    is Response.Error -> {
                        _detailState.update {
                            it.copy(
                                loading = false,
                                error = result.error?.message
                            )
                        }
                    }
                }
            }
        }
    }
}

data class DetailsState(
    val meals: MealX? = null,
    val loading:Boolean=false,
    val error:String?=null
)