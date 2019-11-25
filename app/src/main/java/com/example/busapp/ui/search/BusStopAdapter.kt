package com.example.busapp.ui.search

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.busapp.R
import com.example.busapp.network.data.Item
import com.example.busapp.ui.arrive.ArriveActivity

class BusStopAdapter : RecyclerView.Adapter<BusStopViewHolder>() {

    private var busStopData: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_recyclerview_item, parent, false)
        return BusStopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return busStopData.size
    }

    override fun onBindViewHolder(viewHolder: BusStopViewHolder, position: Int) {
        with(viewHolder) {
            busStopName.text = busStopData[position].nodenm
            itemView.setOnClickListener { v ->
                val intent = Intent(itemView.context, ArriveActivity::class.java)
                intent.putExtra("nodeId", busStopData[position].nodeid)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun updateItems(busStops: List<Item>) {
        this.busStopData = busStops
    }
}