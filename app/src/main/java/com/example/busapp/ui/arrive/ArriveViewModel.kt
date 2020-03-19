package com.example.busapp.ui.arrive

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.busapp.network.data.ArriveItem
import com.example.busapp.ui.search.SearchRepository
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ArriveViewModel {

    val arriveAdapter = ArriveListAdapter()

    private val searchRepository: SearchRepository =
        SearchRepository()

    val isResultEmpty = MutableLiveData<Boolean>()

    fun updateArriveInfo(nodeId: String) {
        val disposable = searchRepository.getArriveInfo(nodeId).subscribe({ arriveInfo ->
            val result = arriveInfo.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                isResultEmpty.value = false
                arriveAdapter.updateArriveList(result)
                arriveAdapter.notifyDataSetChanged()
            } else {
                isResultEmpty.value = true
            }
        }, { throwable ->
            Log.d("ERROR", throwable.toString())
        })
    }
}