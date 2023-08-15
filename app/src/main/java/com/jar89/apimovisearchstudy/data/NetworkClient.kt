package com.jar89.apimovisearchstudy.data

import com.jar89.apimovisearchstudy.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}