package com.example.busapp.ui.arrive

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.arrive_recyclerview_item.view.*

class ArriveViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val busNumber = itemview.bus_number
    val arriveTime = itemview.bus_arrive_time
}