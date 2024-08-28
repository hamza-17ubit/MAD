package com.example.cis4034_n.data.network

import com.example.cis4034_n.data.requests.QuestionRequest
import com.example.cis4034_n.data.response.QuestionsResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

class KtorClient {

    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun fetchItems(questionRequest: QuestionRequest): QuestionsResponse {
        return client.get<QuestionsResponse>{
            url {
                takeFrom(NetworkEndPoints.baseURL + NetworkEndPoints.getQuestions)
                parameters.append("amount", questionRequest.amount.toString())
                parameters.append("category", questionRequest.category)
                parameters.append("difficulty", questionRequest.difficulty)
                parameters.append("type", questionRequest.type)

            }
        }
//        return client.get<QuestionsResponse>(NetworkEndPoints.baseURL+NetworkEndPoints.getQuestions
//                +"?amount=10")
//        return client.get {
//            NetworkEndPoints.baseURL + NetworkEndPoints.getQuestions
//            body = questionRequest
//        }
    }

}