package com.example.networkmodule.api

import com.example.networkmodule.model.DataItem
import com.example.networkmodule.model.Response
import retrofit2.http.GET

interface BookApi {
    @GET(EndPoint.Get_Book)
    suspend fun getBook(): Response
}