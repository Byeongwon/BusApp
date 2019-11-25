package com.example.busapp.ui.arrive

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.busapp.R
import com.example.busapp.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.activity_arrive.*

class ArriveActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel = SearchViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrive)

        initView()
        val extras = intent.extras
        val nodeId = extras?.getString("nodeId") ?: return

        searchViewModel.updateArriveInfo(nodeId)
    }

    private fun initView() {
        arriveInfo_recycler.layoutManager = LinearLayoutManager(applicationContext)
        arriveInfo_recycler.adapter = searchViewModel.arriveAdapter
    }
}