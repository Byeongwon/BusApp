package com.example.busapp.ui.arrive

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.busapp.R
import com.example.busapp.databinding.ActivityArriveBinding
import kotlinx.android.synthetic.main.activity_arrive.*

class ArriveActivity : AppCompatActivity() {

    private lateinit var arriveViewModel: ArriveViewModel
    private lateinit var binding: ActivityArriveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_arrive)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_arrive)
        arriveViewModel = ViewModelProviders.of(this).get(ArriveViewModel::class.java)

        binding.viewModel = arriveViewModel
        binding.lifecycleOwner = this

        initView()
        val extras = intent.extras
        val nodeId = extras?.getString("nodeId") ?: return

        arriveViewModel.updateArriveInfo(nodeId)
    }

    private fun initView() {
        arriveInfo_recycler.layoutManager = LinearLayoutManager(applicationContext)
        arriveInfo_recycler.adapter = arriveViewModel.arriveAdapter
    }

}