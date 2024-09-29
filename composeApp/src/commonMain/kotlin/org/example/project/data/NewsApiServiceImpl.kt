package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.example.project.domain.model.Articles
import org.example.project.domain.model.MainResponseApiNews
import org.example.project.domain.model.MealItem
import org.example.project.domain.model.Meals
import org.example.project.domain.remote.NewsApiService
import org.example.project.domain.remote.RequestCondition
import org.example.project.utils.K
import org.example.project.utils.Response

class NewsApiServiceImpl : NewsApiService {

    companion object {
        val baseUrlApi = "https://newsapi.org/v2/top-headlines"
        val apiKey = "0573b33ff1fa47d6afab4af997188a75"
    }

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=0573b33ff1fa47d6afab4af997188a75

    //https://newsapi.org/v2/everything?q=tesla&from=2024-08-26&sortBy=
// publishedAt&apiKey=0573b33ff1fa47d6afab4af997188a75

    private val httpClient = HttpClient {

        install(Logging)

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 16000
        }
        install(DefaultRequest) {
           /* headers {
                append("country", "us")
                append("apiKey", apiKey)
            }*/
        }

    }


    override suspend fun readTopHeadLine(): Flow<RequestCondition<List<Articles?>>> {
        return flow {
            try {
                emit(RequestCondition.LoadingCondition)
                val response = httpClient.get {
                    url(baseUrlApi)
                    parameter("country", "us")
                    parameter("apiKey", apiKey)
                }
                if (response.status.value == 200) {
                    val json = Json { ignoreUnknownKeys = true }
                    val apiResponseBody =
                        json.decodeFromString<MainResponseApiNews>(response.body())
                    println("Response Api => " + apiResponseBody)

                    val data = apiResponseBody.articles
                    emit(RequestCondition.SuccessCondition(data))

                }

            } catch (e: Exception) {
                println("Response Api => " + e.message.toString())
                emit(RequestCondition.ErrorCondition(e.message.toString()))
            }
        }
    }



    override fun fetchMeals(location: String): Flow<Response<Meals>> {
       return flow {
           emit(Response.Loading())
           val mealDto = httpClient.get{
               url {
                   protocol = URLProtocol.HTTPS
                   host = K.Host
                   path(K.Path)
                   parameters.append("a",location)
               }
           }.body<Meals>()
           emit(Response.Success(mealDto))
       }.catch {
           emit(Response.Error(it))
       }
    }

    override fun fetchMealById(id: String): Flow<Response<MealItem>> {
       return  flow {
           emit(Response.Loading())
           val mealDto = httpClient.get{
               url {
                   protocol = URLProtocol.HTTPS
                   host = K.Host
                   path(K.LookUpPath)
                   parameters.append("i",id)
               }
           }.body<MealItem>()
           emit(Response.Success(mealDto))
       }.catch {
           emit(Response.Error(it))
       }
    }
}