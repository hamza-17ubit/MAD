package com.example.cis4034_n.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cis4034_n.data.localModel.MapperQuestionResponseToLocalQuestions
import com.example.cis4034_n.data.localModel.ResultLocal
import com.example.cis4034_n.data.repositories.QuestionRepository
import com.example.cis4034_n.data.response.QuestionsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    var loading by mutableStateOf(false)
    var questionsResponseData: QuestionsResponse? by mutableStateOf(null)
    var questionList: ArrayList<ResultLocal>? by mutableStateOf(null)
    var score by mutableIntStateOf(0)

    fun getData(category: String, difficulty: String, type: String) {
        loading = true
        viewModelScope.launch(Dispatchers.IO) {
            val value = questionRepository.getData(
                category, difficulty = difficulty, type
            )
            Log.d("ServerDAta", value.toString())
            questionList = value.results?.let { MapperQuestionResponseToLocalQuestions(it) }
            loading = false
        }
    }
}