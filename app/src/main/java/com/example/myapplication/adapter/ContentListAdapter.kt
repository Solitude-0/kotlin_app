package com.example.myapplication.adapter


import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.common.BaseAdapter
import com.example.myapplication.domain.Content

class ContentListAdapter : BaseAdapter<Content>(R.layout.content_item_list, { view, item ->
    (view.findViewById<TextView>(R.id.item_demo)).text = item.title
})
