package com.example.networkmodule.usecase

import android.content.res.Resources
import android.util.Log
import com.example.networkmodule.model.DataItem
import com.example.networkmodule.model.Response
import com.example.networkmodule.network.Resource
import com.example.networkmodule.repository.BookRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke() : Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading())
            val result=repository.getBookData()
            Log.d("rescheck",""+result)
            emit(Resource.Success(result))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't connect to server"))
        }
    }
}