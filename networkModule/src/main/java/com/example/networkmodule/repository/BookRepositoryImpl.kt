package com.example.networkmodule.repository

import com.example.networkmodule.api.BookApi
import com.example.networkmodule.model.DataItem
import com.example.networkmodule.model.Response
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api:BookApi
) :BookRepository{
    override suspend fun getBookData(): Response =api.getBook()
}