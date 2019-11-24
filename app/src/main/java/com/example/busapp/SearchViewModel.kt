package com.example.busapp

import android.util.Log
import com.example.busapp.network.data.Item

class SearchViewModel {

    private var busStopList = listOf<Item>()
    val adapter = BusStopAdapter()

    private val searchRepository: SearchRepository = SearchRepository()

    init {
        updateBusStops()
    }

    private fun updateBusStops() {
        val disposable = searchRepository.getBusStops.subscribe({
            busStops ->
            val result = busStops.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                busStopList = result
                adapter.updateItems(busStopList)
                adapter.notifyDataSetChanged()
            }
        }, {
            throwable ->
                Log.d("ERROR", throwable.toString())
        })
    }

    fun searchBusStop(name: String) {
        val result = busStopList.filter { it.nodenm.contains(name)}
        adapter.updateItems(result)
        adapter.notifyDataSetChanged()
    }
}