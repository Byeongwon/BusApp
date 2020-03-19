package com.example.busapp.ui.favorite

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.busapp.R
import com.example.busapp.databinding.ItemFavoriteBinding
import com.example.busapp.ui.search.model.BusStop

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var favoriteList: List<BusStop>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFavoriteBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_favorite,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteList[position])
        with(holder) {
            itemView.setOnClickListener { v ->
                val intent = android.content.Intent(
                    itemView.context,
                    com.example.busapp.ui.arrive.ArriveActivity::class.java
                )
                intent.putExtra("nodeId", favoriteList[position].id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (::favoriteList.isInitialized) favoriteList.size else 0
    }

    fun updateArriveList(arriveList: List<BusStop>) {
        this.favoriteList = arriveList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(busStop: BusStop) {
            binding.viewModel = busStop
        }
    }
}