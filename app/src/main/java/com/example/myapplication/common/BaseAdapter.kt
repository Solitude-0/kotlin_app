package com.example.myapplication.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Content

abstract class BaseAdapter<T : BaseModel>(
    private val layoutId: Int,
    private val bind: (View, T) -> Unit
) : RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {
    private var items: List<T> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        bind(holder.itemView, item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(dataList: List<T>) {
        items = dataList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}