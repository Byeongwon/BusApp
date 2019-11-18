package com.example.busapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.busapp.network.NetworkManager
import com.example.busapp.network.data.BusStop
import com.example.busapp.network.data.Response
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMenuClickListener()
        createBusStopData()
    }

    private fun setMenuClickListener() {
        first_menu.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    private fun createBusStopData() {
        val str = "ciQLMBFjiJOBw4loD00NCS6fod8n3He99MysmrVuHvQxiSEou9CThpr0gmguv7TiXSI3vyjECcbmMMHAdu+3+w=="
        val call: Call<BusStop> = NetworkManager.getBusStopInfoService()
            .getBusStopInfo(
                str,
                22, "대구은행역", 4846)
        call.enqueue(object : Callback<BusStop> {
            override fun onFailure(call: Call<BusStop>, t: Throwable) {
                val str = "ciQLMBFjiJOBw4loD00NCS6fod8n3He99MysmrVuHvQxiSEou9CThpr0gmguv7TiXSI3vyjECcbmMMHAdu+3+w=="
            }

            override fun onResponse(call: Call<BusStop>, response: retrofit2.Response<BusStop>) {
                val busStopList = response.body()?.response

            }
        })
    }
}
