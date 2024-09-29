package org.example.project.domain.remote

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.model.Articles
import org.example.project.domain.model.MealItem
import org.example.project.domain.model.Meals
import org.example.project.utils.Response

interface NewsApiService {

    suspend fun readTopHeadLine(): Flow<RequestCondition<List<Articles?>>>

    fun fetchMeals(location:String="British"):Flow<Response<Meals>>

    fun fetchMealById(id:String):Flow<Response<MealItem>>

}