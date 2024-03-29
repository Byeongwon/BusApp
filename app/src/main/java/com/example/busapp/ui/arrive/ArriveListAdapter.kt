package com.example.busapp.ui.arrive

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.busapp.R
import com.example.busapp.databinding.ItemArriveBinding
import com.example.busapp.network.data.ArriveItem

class ArriveListAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<ArriveListAdapter.ViewHolder>() {

    private lateinit var arriveList: List<ArriveItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArriveBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_arrive, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arriveList[position])
    }

    override fun getItemCount(): Int {
        return if(::arriveList.isInitialized) arriveList.size else 0
    }

    fun updateArriveList(arriveList: List<ArriveItem>) {
        this.arriveList = arriveList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArriveBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ArriveItemViewModel()

        fun bind(arriveItem: ArriveItem) {
            viewModel.bind(arriveItem)
            binding.viewModel = viewModel
        }
    }
}