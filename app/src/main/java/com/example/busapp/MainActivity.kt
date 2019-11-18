package com.example.busapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        hideActionBar()
        changeStatusBarColor()
        setMenuClickListener()
    }

    private fun setMenuClickListener() {
        first_menu.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
    
    private fun changeStatusBarColor() {
        window.run {
            this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            this.statusBarColor = getColor(R.color.appMainColor)
        }
    }
}
