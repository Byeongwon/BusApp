package com.example.busapp.ui.search

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.example.busapp.R
import com.example.busapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        binding.busStopRecycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.busStopRecycler.adapter = viewModel.adapter
        binding.inputBusStopSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty() && s.length >= 2) {
                    viewModel.searchBusStop(s.toString())
                }
            }
        })
    }
}