package com.example.networkmodule.repository

import com.example.networkmodule.model.DataItem
import com.example.networkmodule.model.Response

interface BookRepository {
   suspend fun getBookData(): Response
}