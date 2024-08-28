package com.example.cis4034_n.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionsResponse(
    @SerialName("response_code")
    var responseCode: Int? = null,
    @SerialName("results")
    var results: ArrayList<Results>? = null
)