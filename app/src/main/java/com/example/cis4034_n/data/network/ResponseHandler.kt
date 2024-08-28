package com.example.cis4034_n.data.network

import com.example.cis4034_n.data.response.ErrorModel

interface ResponseHandler {
    fun <T> onSuccess(response: T?)
    fun onFailure(t: ErrorModel?)
}