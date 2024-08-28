package com.example.cis4034_n.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Results(

    @SerialName("type") var type: String? = null,
    @SerialName("difficulty") var difficulty: String? = null,
    @SerialName("category") var category: String? = null,
    @SerialName("question") var question: String? = null,
    @SerialName("correct_answer") var correctAnswer: String? = null,
    @SerialName("incorrect_answers") var incorrectAnswers: ArrayList<String> = arrayListOf()

)
