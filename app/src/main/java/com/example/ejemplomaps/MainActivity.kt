package com.example.ejemplomaps

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var map: GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()


        val ib1 = findViewById<ImageButton>(R.id.im1)
        val ib2 = findViewById<ImageButton>(R.id.im2)
        val ib3 = findViewById<ImageButton>(R.id.im3)
        val ib4 = findViewById<ImageButton>(R.id.im4)

        ib1.setOnClickListener {

            val coordenadas = LatLng(19.537815403842323,-99.1768176635963)

            val marker = MarkerOptions().position(coordenadas).title("Terrazas Belvedere")
            map.addMarker(marker)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(19.537815403842323,-99.1768176635963),19.0f))

        }
        ib2.setOnClickListener {

            val coordenadas = LatLng(19.615404548204815,-99.30005036845735)
            val marker = MarkerOptions().position(coordenadas).title("Sabores de México")
            map.addMarker(marker)
            map.addMarker(marker)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(19.615404548204815,-99.30005036845735),19.0f))
        }
        ib3.setOnClickListener {

            val coordenadas = LatLng( 19.4375975860069,-99.15321122650757)
            val marker = MarkerOptions().position(coordenadas).title("La distral")
            map.addMarker(marker)
            map.addMarker(marker)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng( 19.4375975860069,-99.15321122650757),19.0f))
        }
        ib4.setOnClickListener {

            val coordenadas = LatLng(  19.438757607694228,-99.13524948442199)
            val marker = MarkerOptions().position(coordenadas).title("El cardenal")
            map.addMarker(marker)
            map.addMarker(marker)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(  19.438757607694228,-99.13524948442199),19.0f))
        }



    }

    private fun enableLocation(){
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        crearMarker()
        enableLocation()
    }

    private fun crearMarker(){
        val coordenadas = LatLng(19.6258017,-99.2896475)
        val marker = MarkerOptions().position(coordenadas).title("Aquí estoy")
        map.addMarker(marker)
    }

    private fun isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }


    }

}