package com.example.busapp.ui.search

import android.util.Log
import com.example.busapp.network.data.Item
import com.example.busapp.ui.arrive.ArriveAdapter

class SearchViewModel {

    private var busStopList = listOf<Item>()
    val adapter = BusStopAdapter()
    val arriveAdapter = ArriveAdapter()

    private val searchRepository: SearchRepository =
        SearchRepository()

    init {
        updateBusStops()
    }

    private fun updateBusStops() {
        val disposable = searchRepository.getBusStops.subscribe({ busStops ->
            val result = busStops.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                busStopList = result
                adapter.updateItems(busStopList)
                adapter.notifyDataSetChanged()
            }
        }, { throwable ->
            Log.d("ERROR", throwable.toString())
        })
    }

    fun updateArriveInfo(nodeId: String) {
        val disposable = searchRepository.getArriveInfo(nodeId).subscribe({ arriveInfo ->
            val result = arriveInfo.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                arriveAdapter.updateItems(result)
                arriveAdapter.notifyDataSetChanged()
            }
        }, { throwable ->
            Log.d("ERROR", throwable.toString())
        })
    }

    fun searchBusStop(name: String) {
        val result = busStopList.filter { it.nodenm.contains(name) }
        adapter.updateItems(result)
        adapter.notifyDataSetChanged()
    }
}