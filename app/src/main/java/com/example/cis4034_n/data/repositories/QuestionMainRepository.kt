package com.example.cis4034_n.data.repositories

import android.util.Log
import com.example.cis4034_n.data.network.KtorClient
import com.example.cis4034_n.data.requests.QuestionRequest
import com.example.cis4034_n.data.response.QuestionsResponse
import javax.inject.Inject

class QuestionMainRepository @Inject constructor(
    private val ktorClient: KtorClient
) : QuestionRepository {

    override suspend fun getData(category: String, difficulty: String, type: String): QuestionsResponse {

        val questionRequest = QuestionRequest(
            category = category,
            difficulty =  difficulty,
            type = type
        )
        val result = ktorClient.fetchItems(questionRequest)
        Log.d("ResultData", "$result")
        return result
    }
}