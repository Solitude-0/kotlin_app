package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.ItemDemo

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var items: List<ItemDemo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.bind(user)
    }

    fun submitList(dataList: List<ItemDemo>?) {
        items = dataList ?: emptyList()
        notifyDataSetChanged()
    }

    // ViewHolder类，负责持有和绑定每个条目的视图
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.item_demo)

        fun bind(item: ItemDemo) {
            nameTextView.text = item.title
        }
    }
}