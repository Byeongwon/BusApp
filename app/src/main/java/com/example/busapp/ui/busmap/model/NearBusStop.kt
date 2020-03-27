package com.example.busapp.ui.busmap.model

import com.example.busapp.network.data.Item

data class NearBusStop constructor(
    /**
     * 정류소명
     */
    val name: String,
    /**
     * 정류소 ID
     */
    val id: String,

    val latitude: Double,

    val longitude: Double

) {

    companion object {

        fun createFrom(item: Item): NearBusStop {
            return NearBusStop(item.nodenm, item.nodeid, item.gpslati, item.gpslong)
        }
    }
}