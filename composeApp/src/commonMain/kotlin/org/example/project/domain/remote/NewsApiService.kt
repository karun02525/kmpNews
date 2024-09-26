package org.example.project.domain.remote

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.model.Articles

interface NewsApiService {
    suspend fun readTopHeadLine(): Flow<RequestCondition<List<Articles?>>>
}