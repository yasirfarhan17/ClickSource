package com.example.clicksource.ui.mainActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.clicksource.databinding.IndiviewBinding
import com.example.networkmodule.model.DataItem
import java.util.*
import kotlin.collections.ArrayList

class BookAdapter(private val callback : ItemAdapterCallback):
RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val items=ArrayList<DataItem>()
    private val itemall=ArrayList<DataItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list : ArrayList<DataItem>){
        items.clear()
        itemall.clear()
        items.addAll(list)
        itemall.addAll(list)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun restoreAllList() {
        items.addAll(itemall)
        notifyDataSetChanged()
    }

    inner class BookViewHolder(private val binding : IndiviewBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item : DataItem){
            with(binding){
                tvBody.text=item.body
                tvTitle.text=item.title
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.BookViewHolder {
        val binding = IndiviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size



}
interface ItemAdapterCallback {
    fun onItemClick(bookItem :DataItem)
}