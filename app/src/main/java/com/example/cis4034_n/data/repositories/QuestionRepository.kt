package com.example.cis4034_n.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.cis4034_n.data.response.QuestionsResponse

interface QuestionRepository {

    ///for testing use in future.
    val questionsResponseListData: MutableLiveData<QuestionsResponse>
        get() = MutableLiveData<QuestionsResponse>()

    suspend fun getData(category: String, difficulty: String, type: String): QuestionsResponse
}