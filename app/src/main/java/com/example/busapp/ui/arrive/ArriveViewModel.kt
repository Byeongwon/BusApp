package com.example.busapp.ui.arrive

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.busapp.network.data.ArriveItem
import com.example.busapp.ui.search.SearchRepository
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ArriveViewModel: ViewModel() {

    val arriveAdapter = ArriveListAdapter()

    private val searchRepository: SearchRepository =
        SearchRepository()

    var isResultEmpty: MutableLiveData<Int> = MutableLiveData()

    fun updateArriveInfo(nodeId: String) {
        val disposable = searchRepository.getArriveInfo(nodeId).subscribe({ arriveInfo ->
            val result = arriveInfo.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                isResultEmpty.value = View.GONE
                arriveAdapter.updateArriveList(result)
                arriveAdapter.notifyDataSetChanged()
            } else {
                isResultEmpty.value = View.VISIBLE
            }
        }, { throwable ->
            Log.d("ERROR", throwable.toString())
        })
    }
}