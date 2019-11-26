package com.example.busapp.ui.arrive

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.busapp.R
import com.example.busapp.network.data.ArriveItem
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ArriveAdapter: RecyclerView.Adapter<ArriveViewHolder>() {
    private var arriveData: List<ArriveItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArriveViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.arrive_recyclerview_item, parent, false)
        return ArriveViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arriveData.size
    }

    override fun onBindViewHolder(viewHolder: ArriveViewHolder, position: Int) {
        with(viewHolder) {
            busNumber.text = arriveData[position].routeno
            arriveTime.text = longToDate(arriveData[position].arrtime)
        }
    }

    fun updateItems(arriveData: List<ArriveItem>) {
        this.arriveData = arriveData
    }

    fun longToDate(mills: Long): String {
        val pattern = "mm분ss초 전"
        val format = SimpleDateFormat(pattern)
        return format.format(Timestamp(mills * 1000))
    }
}