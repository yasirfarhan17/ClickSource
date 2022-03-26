package com.example.clicksource.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clicksource.R
import com.example.clicksource.databinding.ActivityMainBinding
import com.example.clicksource.util.UiUtil
import com.example.networkmodule.model.DataItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ItemAdapterCallback {

    private lateinit var binding : ActivityMainBinding
    private val viewModel :MainViewModel by viewModels()
    private lateinit var  uiUtil: UiUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        uiUtil= UiUtil(this)
        initUi()
        addListner()
        addobserver()
    }

    private fun addobserver() {
       observeBookList()
        observeSearchList()
    }

    private fun observeSearchList() {
        viewModel.searchResult.observe(this){
            (binding.rvHome.adapter as BookAdapter).submitList(it)
        }
    }

    private fun observeBookList() {
        viewModel.bookList.observe(this){
          // Toast.makeText(this,"checking"+it.data,Toast.LENGTH_SHORT).show()
            (binding.rvHome.adapter as BookAdapter).submitList(it.data as ArrayList<DataItem>)
        }
    }

    private fun addListner() {




    }

    private fun initUi() {

        with(binding){
            rvHome.layoutManager = LinearLayoutManager(this@MainActivity)
            rvHome.adapter = BookAdapter(this@MainActivity)
            viewModel.getBookList()

        }



    }

    override fun onItemClick(bookItem: DataItem) {
        TODO("Not yet implemented")
    }
}