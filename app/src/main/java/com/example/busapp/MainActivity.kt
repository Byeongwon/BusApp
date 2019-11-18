package com.example.busapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideActionBar()
        changeStatusBarColor()
//        setSingleEvent(mainGrid)

    }

//    private fun setSingleEvent(gridLayout: GridLayout) {
//        for (i in 0..gridLayout.childCount) {
//            val cardView = gridLayout.getChildAt(i) as CardView
//            cardView.setOnClickListener {
//                Toast.makeText(this, "Selected Index $i", Toast.LENGTH_LONG).show()
//            }
//        }
//    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun changeStatusBarColor() {
        window.run {
            this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            this.statusBarColor = getColor(R.color.appMainColor)
        }
    }
}
