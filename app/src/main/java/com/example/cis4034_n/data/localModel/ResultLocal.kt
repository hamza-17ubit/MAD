package com.example.cis4034_n.data.localModel

data class ResultLocal(
    var type: String? = null,
    var difficulty: String? = null,
    var category: String? = null,
    var question: String? = null,
    var correctAnswer: String? = null,
    var options: ArrayList<String> = arrayListOf(),
    var correctIndex: Int = 0,
    var selectedIndex: Int = -1,
)