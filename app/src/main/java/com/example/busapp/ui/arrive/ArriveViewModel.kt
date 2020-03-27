package com.example.busapp.ui.arrive

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.busapp.ui.search.SearchRepository

class ArriveViewModel : ViewModel() {

    val arriveAdapter = ArriveListAdapter()

    private val searchRepository: SearchRepository =
        SearchRepository()

    var isResultEmpty: MutableLiveData<Int> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    fun updateArriveInfo(nodeId: String) {
        loadingVisibility.value = View.VISIBLE

        val disposable = searchRepository.getArriveInfo(nodeId).subscribe({ arriveInfo ->
            loadingVisibility.value = View.GONE
            val result = arriveInfo.response?.body?.items?.item
            if (!result.isNullOrEmpty()) {
                isResultEmpty.value = View.GONE
                arriveAdapter.updateArriveList(result)
                arriveAdapter.notifyDataSetChanged()
            } else {
                isResultEmpty.value = View.VISIBLE
            }
        }, { throwable ->
            loadingVisibility.value = View.GONE
            isResultEmpty.value = View.VISIBLE
            Log.d("ERROR", throwable.toString())
        })
    }
}