package com.example.cws_project

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {

    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_location)
        val i=intent
        val lat=i.getDoubleExtra("lat",0.0)
        val lng=i.getDoubleExtra("lng",0.0)
        val name=i.getStringExtra("name")
        var loc=LatLng(lat,lng)
        initmap(loc,name)
        ok.setOnClickListener {
            finish()
        }

    }

    fun initmap(loc:LatLng,name:String){
        val mapFragment=supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{
            googleMap=it
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,16.0f))
            googleMap.setMinZoomPreference(10.0f) //최대로 축소할 수 있는 정도
            googleMap.setMaxZoomPreference(18.0f) //최대로 확대할 수 있는 정도
            val options= MarkerOptions()
            options.position(loc)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            options.title(name)
            val mk=googleMap.addMarker(options)
            mk.showInfoWindow()
        }
    }
}
