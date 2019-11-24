package com.example.busapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.busapp.network.data.Item

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
                android.widget.Toast.makeText(
                    v.context,
                    position.toString(),
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun updateItems(busStops: List<Item>) {
        this.busStopData = busStops
    }
    fun changeData(busStopList: ArrayList<Item>) {
        this.notifyDataSetChanged()
    }
}