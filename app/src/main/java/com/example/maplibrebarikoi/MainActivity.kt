package com.example.maplibrebarikoi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.maplibrebarikoi.model.SearchResponseModel
import com.example.maplibrebarikoi.network.RetrofitApi
import com.example.maplibrebarikoi.network.RetrofitBuilder
import com.example.maplibrebarikoi.utils.AppConstants
import com.example.xmaplibrebarikoi.databinding.ActivityMainBinding
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView
    var lat: Double = 23.8103
    var lon: Double = 90.4125

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapView

        mapView.getMapAsync { mapboxMap ->
            // Customize the map style if needed
            mapboxMap.setStyle(AppConstants.baseUrlStyle) {
                // Set initial map view
                setMapView(mapboxMap)
            }

        }

        binding.button.setOnClickListener {

            val api = RetrofitBuilder().getInstance().create(RetrofitApi::class.java)
            // launching a new coroutine
            GlobalScope.launch {
                api.getNearbyBanks(
                    API_KEY = AppConstants.apiKey,
                    distance = 1,
                    limit = 20,
                    latitude = lat,
                    longitude = lon,
                    ptype = "bank"
                ).enqueue(object : Callback<SearchResponseModel> {
                    override fun onResponse(
                        call: Call<SearchResponseModel>,
                        response: Response<SearchResponseModel>
                    ) {
                        // Handle the response
                        Log.d("ayush: ", response.body().toString())

                        runOnUiThread {

                            mapView.getMapAsync { mapboxMap ->
                                // Define the coordinates and zoom level for the camera
                                val location = LatLng(lat, lon)

                                mapView.getMapAsync { mapboxMap ->
                                    mapboxMap.moveCamera(
                                        CameraUpdateFactory.newCameraPosition(
                                            CameraPosition.Builder()
                                                .target(location)
                                                .zoom(13.0)
                                                .build()
                                        )
                                    )
                                    for (position in response.body()?.places!!) {
                                        val markerOptions = MarkerOptions()
                                            .setPosition(
                                                LatLng(
                                                    position.latitude as Double,
                                                    position.longitude as Double
                                                )
                                            )
                                            .setTitle(position.name)
                                            .setSnippet(position.Address)

                                        // Add the marker to the map
                                        mapboxMap.addMarker(markerOptions)
                                    }

                                }
                            }


                        }
                    }

                    override fun onFailure(call: Call<SearchResponseModel>, t: Throwable) {
                        // Handle the failure
                        Log.d("onFailure: ", t.printStackTrace().toString())
                    }
                })

            }

        }

    }


    private fun setMapView(mapboxMap: MapboxMap) {
        // Move the camera to the specified location and zoom level
        mapboxMap.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.Builder()
                    .build()
            )
        )
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()

    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}



