package com.example.busapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMenuClickListener()
    }

    private fun setMenuClickListener() {
        first_menu.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}
