package com.example.busapp.ui.search

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.search_recyclerview_item.view.*

class BusStopViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    val busStopName = itemView.busstop_name
    val favoriteIcon = itemView.right_icon
}