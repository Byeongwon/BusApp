package com.example.busapp.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.busapp.R
import com.example.busapp.utils.FavoriteDataManager
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private val favoriteAdapter = FavoriteAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initView()
        initData()
    }

    private fun initView() {
        favorite_recycler.adapter = favoriteAdapter
    }

    private fun initData() {
        favoriteAdapter.updateArriveList(FavoriteDataManager.getFavoriteData(applicationContext))
    }
}