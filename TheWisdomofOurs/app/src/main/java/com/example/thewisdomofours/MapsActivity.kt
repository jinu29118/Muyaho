package com.example.thewisdomofours

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    var loc2 = LatLng(52.5186, 13.4081)
    var loc = LatLng(37.554752, 126.970631)
    var meetLoc = LatLng(0.0,0.0)
    val database = Database
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    var startupdate=false
    private lateinit var googleMap: GoogleMap
    lateinit var binding: ActivityMapsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapsBinding.inflate(layoutInflater)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        setContentView(binding.root)
        initmap()
        initSpinner()
        init()
    }

    fun initLocation(){
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create().apply {
            interval=10000
            fastestInterval=5000
            priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback=object :LocationCallback(){
            override fun onLocationResult(location: LocationResult) {
                if(location.locations.size==0) return
                loc = LatLng(location.locations[location.locations.size-1].latitude,
                    location.locations[location.locations.size-1].longitude)
                setCurrentLocation(loc)
                Log.i("location","LocationCallback()")
            }
        }
    }
    fun setCurrentLocation(location: LatLng){
        val option = MarkerOptions()
        option.position(loc)
        option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        googleMap.addMarker(option)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc2,10.0f))
    }

    /*override fun onRequestPermissionsResult(){
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startLocationUpdates()
            }else{
                Toast.makeText(this,"위치정보를 제공하셔야합니다.", Toast.LENGTH_SHORT).show()
                setCurrentLocation(loc)
            }
        }
    }*/
/*
    private fun startLocationUpdates() {
        TODO("Not yet implemented")
    }
*/
    private fun initSpinner(){
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())
        adapter.add("Hybrid")
        adapter.add("Normal")
        adapter.add("Satellite")
        adapter.add("Terrain")
        binding.apply {
            spinner.adapter=adapter
            spinner.setSelection(1)
            spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0->{
                            googleMap.mapType=GoogleMap.MAP_TYPE_HYBRID
                        }
                        1->{
                            googleMap.mapType=GoogleMap.MAP_TYPE_NORMAL
                        }
                        2->{
                            googleMap.mapType=GoogleMap.MAP_TYPE_SATELLITE
                        }
                        3->{
                            googleMap.mapType=GoogleMap.MAP_TYPE_TERRAIN
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    googleMap.mapType=GoogleMap.MAP_TYPE_NORMAL
                }
            }
        }
    }
    private fun initmap(){
        initLocation()
        val mapFragment=supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap=it
            /*googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc2,10.0f))
            googleMap.setMinZoomPreference(5.0f)
            googleMap.setMaxZoomPreference(18.0f)
            val option = MarkerOptions()
            option.position(loc2)
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            option.title("독일")
            option.snippet("베를린")
            val mk1=googleMap.addMarker(option)
            mk1.showInfoWindow()*/
            initMapListener()
        }
    }
    private fun initMapListener(){
        googleMap.setOnMapClickListener {
            meetLoc=(it)
            googleMap.clear()
            val option = MarkerOptions()
            option.position(it)
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            googleMap.addMarker(option)

        }
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap=p0
    }
    private fun init(){
        val acpbtn = findViewById<Button>(R.id.button)
        val bckbtn = findViewById<Button>(R.id.button2)
        acpbtn.setOnClickListener{
            //database.setloc(meetLoc)
            onBackPressed()
        }
        bckbtn.setOnClickListener {
            onBackPressed()
        }
    }
}