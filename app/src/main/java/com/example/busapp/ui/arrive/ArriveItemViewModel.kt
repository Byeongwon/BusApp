package com.example.busapp.ui.arrive

import androidx.lifecycle.MutableLiveData
import com.example.busapp.network.data.ArriveItem
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ArriveItemViewModel {

    /**
     * 버스 번호
     */
    val busNumber = MutableLiveData<String>()
    /**
     * 버스 타입
     */
    val busType = MutableLiveData<String>()
    /**
     * 도착 시간
     */
    val arriveTime = MutableLiveData<String>()
    /**
     * 남은 정류장 개수
     */
    val remainStationCount = MutableLiveData<String>()


    fun bind(arriveItem: ArriveItem) {
        busNumber.value = arriveItem.routeno
        busType.value = setBusType(arriveItem.routetp, arriveItem.vehicletp)
        arriveTime.value = setArriveTime(arriveItem.arrtime)
        remainStationCount.value = setRemainStationCount(arriveItem.arrprevstationcnt)
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