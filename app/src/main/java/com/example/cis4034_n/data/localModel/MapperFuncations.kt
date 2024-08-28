package com.example.cis4034_n.data.localModel

import com.example.cis4034_n.data.response.Results


fun MapperQuestionResponseToLocalQuestions(result: List<Results>): ArrayList<ResultLocal> {

    val resultLocalList = ArrayList<ResultLocal>()

    result.forEach {
        val resultLocal = ResultLocal(
            it.type,
            it.difficulty,
            it.category,
            it.question,
            it.correctAnswer,
            it.incorrectAnswers
        )
        it.correctAnswer?.let { it1 -> resultLocal.options.add(it1) }
        resultLocal.options.shuffle()
        resultLocal.options.forEachIndexed { index, value  ->
            if (value == resultLocal.correctAnswer) {
                resultLocal.correctIndex = index
            }
        }
        resultLocalList.add(resultLocal)
    }
    return  resultLocalList
}