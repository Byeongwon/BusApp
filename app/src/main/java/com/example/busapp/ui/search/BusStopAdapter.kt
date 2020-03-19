package com.example.busapp.ui.search

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.busapp.R
import com.example.busapp.network.data.Item
import com.example.busapp.ui.arrive.ArriveActivity
import com.example.busapp.ui.favorite.model.BusStop
import com.example.busapp.utils.FavoriteDataManager

class BusStopAdapter : RecyclerView.Adapter<BusStopViewHolder>() {

    private var busStopData: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_recyclerview_item, parent, false)
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
            favoriteIcon.setOnClickListener {
                val isUpdated = FavoriteDataManager.updateFavoriteData(
                    itemView.context,
                    BusStop(busStopData[position].nodenm, busStopData[position].nodeid)
                )
                if (!isUpdated) {
                    Toast.makeText(itemView.context, "이미 등록된 정류장입니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(itemView.context, "즐겨찾기 등록 되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun updateItems(busStops: List<Item>) {
        this.busStopData = busStops
    }
}