package com.example.busapp.ui.busmap

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.busapp.R
import com.example.busapp.databinding.ActivityBusmapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.busapp.location.LocationProvider

class BusMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var viewModel: BusMapViewModel
    private lateinit var binding: ActivityBusmapBinding
    private val busMapHelper = BusMapHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initMapView()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_busmap)
        viewModel = ViewModelProviders.of(this).get(BusMapViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initMapView() {
        val mapFragment: SupportMapFragment? =
            supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val latLng = LocationProvider.getInstance().getLastLocationLatlng()
        busMapHelper.setGoogleMap(googleMap)
        busMapHelper.setCenterPoint(latLng)
    }
}