package com.example.busapp.ui.search

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.search_recyclerview_item.view.*

class BusStopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val busStopName = itemView.busstop_name
}