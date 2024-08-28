package com.example.cis4034_n.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class QuestionRequest(
    var amount: Int = 10,
    var category: String = "",
    var difficulty: String = "",
    var type: String = ""
)
