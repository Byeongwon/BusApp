package com.example.busapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.busapp.network.data.Item
import com.example.busapp.ui.favorite.model.BusStop

class SearchViewModel : ViewModel() {

    private var busStopList = listOf<Item>()
    val adapter = BusStopAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private val searchRepository: SearchRepository =
        SearchRepository()

    init {
        updateBusStops()
    }

    private fun updateBusStops() {
        loadingVisibility.value = View.VISIBLE
        val disposable = searchRepository.getBusStops.subscribe({ busStops ->
            loadingVisibility.value = View.GONE
            val result = busStops.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                busStopList = result
            }
        }, { throwable ->
            loadingVisibility.value = View.GONE
            Log.d("ERROR", throwable.toString())
        })
    }

    fun searchBusStop(name: String) {
        val result = busStopList.filter { it.nodenm.contains(name) }.map { BusStop.createFrom(it) }
        adapter.updateItems(result)
        adapter.notifyDataSetChanged()
    }
}