package com.example.busapp.ui.favorite.model

import com.example.busapp.network.data.Item

/**
 * 정류소 정보
 */
data class BusStop constructor(
    /**
     * 정류소명
     */
    val name: String,
    /**
     * 정류소 ID
     */
    val id: String
) {

    companion object {

        fun createFrom(item: Item): BusStop {
            return BusStop(item.nodenm, item.nodeid)
        }
    }
}