package com.example.busapp.ui.arrive

import android.arch.lifecycle.MutableLiveData
import com.example.busapp.network.data.ArriveItem
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ArriveViewModel {

    val busNumber = MutableLiveData<String>()
    val busType = MutableLiveData<String>()
    val arriveTime = MutableLiveData<String>()
    val remainStationCount = MutableLiveData<String>()

    fun bind(data: ArriveItem) {
        busNumber.value = data.routeno
        busType.value = setBusType(data.routetp, data.vehicletp)
        arriveTime.value = setArriveTime(data.arrtime)
        remainStationCount.value = setRemainStationCount(data.arrprevstationcnt)
    }

    private fun setBusType(routeType: String, vehicleType: String): String {
        return "$routeType/${vehicleType.substring(0, 2)}"
    }

    private fun setArriveTime(arriveTime: Long): String {
        val stringPatten: String = if (arriveTime < 60) "ss초 전" else "m분 ss초 전"
        val dateFormat = SimpleDateFormat(stringPatten)
        return dateFormat.format(Timestamp(arriveTime * 1000))
    }

    private fun setRemainStationCount(count: Int): String {
        return "${count}번째 전"
    }
}