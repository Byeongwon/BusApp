package com.example.busapp.ui.arrive

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.busapp.R
import kotlinx.android.synthetic.main.activity_arrive.*

class ArriveActivity : AppCompatActivity() {

    private val arriveViewModel: ArriveViewModel = ArriveViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrive)

        initView()
        val extras = intent.extras
        val nodeId = extras?.getString("nodeId") ?: return

        arriveViewModel.updateArriveInfo(nodeId)
        arriveViewModel.isResultEmpty.observe(this, Observer {
            val empty = it!!
            if (empty) {
                arrive_info_empty.visibility = View.VISIBLE
            } else {
                arrive_info_empty.visibility = View.GONE
            }
        })
    }

    private fun initView() {
        arriveInfo_recycler.layoutManager = LinearLayoutManager(applicationContext)
        arriveInfo_recycler.adapter = arriveViewModel.arriveAdapter
    }

}