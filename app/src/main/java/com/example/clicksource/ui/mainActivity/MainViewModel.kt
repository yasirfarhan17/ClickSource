package com.example.clicksource.ui.mainActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkmodule.usecase.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.clicksource.Base.ViewState
import com.example.clicksource.util.toLiveData
import com.example.networkmodule.model.DataItem
import com.example.networkmodule.model.Response
import com.example.networkmodule.network.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBook: BookUseCase
):ViewModel(){
    private val _viewState = MutableLiveData<ViewState>()
    val viewState = _viewState.toLiveData()

    private val _bookList=MutableLiveData<Response>()
    val bookList = _bookList.toLiveData()
    private val _setError = MutableLiveData<String>()
    val setError = _setError.toLiveData()
    private var _searchResult = MutableLiveData<ArrayList<DataItem>>()
    val searchResult = _searchResult.toLiveData()



    fun getBookList(){
        getBook().onEach {
            _viewState.postValue(ViewState.Loading)
            when(it){
                is Resource.Success ->{
                    Log.d("checkk",""+it.data)
                    _bookList.postValue(it.data)
                    _viewState.postValue(ViewState.Success())
                }
                is Resource.Error ->{
                    _setError.postValue(it.message)

                }
                is Resource.Loading ->{
                    _viewState.postValue(ViewState.Loading)
                }
            }
        }.launchIn(viewModelScope + exceptionHandler)
    }

    private val exceptionHandler = CoroutineExceptionHandler{_,exception ->
        handleFailure(throwable = exception)

    }
    private fun handleFailure(throwable: Throwable ?){
        _viewState.postValue(ViewState.Error(throwable))
        Log.e("Network_error",throwable.toString())
    }

    fun searchList(name: String) {
        viewModelScope.launch(Dispatchers.Default) {
            searchItemInList(name)


        }

    }

    private suspend fun searchItemInList(name: String) = withContext(Dispatchers.Default){
        val localList = ArrayList<DataItem>()
        bookList.value?.data?.forEach {
            if(it?.title?.contains(name,true)==true){
                localList.add(it)
            }
             if (it?.body?.contains(name,true)==true){
                localList.add(it);
            }

            _searchResult.postValue(localList)
            return@withContext
        }
    }

    fun restoreList() {
        getBookList()

    }
}